#!/usr/bin/env python

import yfinance as yf
import matplotlib.pyplot as plt

sp500 = yf.Ticker("^GSPC") # initialize ticker class, S&P 500 ticker symbol

sp500 = sp500.history(period="max") # query all data starting at the beginning of 1990

del sp500["Dividends"]
del sp500["Stock Splits"]

sp500["Tomorrow"] = sp500["Close"].shift(-1) 
""" 
Create a new column that displays tomorrows price 
The current day will not show tomorrows price, which is what we want to predict. 
"""

sp500["Target"] = (sp500["Tomorrow"] > sp500["Close"]).astype(int) # Create column that shows if,
                                                                   # tommorrows price > close price
                                                                   # 1-increases tomorrow ; 0 - decreases tomorrow

sp500 = sp500.loc["1990-01-01":].copy() # taking data from 1990 and later, since we don't want data,
                                        # that's too old where the economy was very different fundamentally,
                                        # which may not be as useful in making future predictions

from sklearn.ensemble import RandomForestClassifier
"""
Random Forest trains individual decision trees with randomized parameters and then averages the results from those decision trees.
This model is resistant and harder to overfit. It also runs relatively quickly and can pick up nonlinear tendencies in the data. 

For example, the open price is not linearly correlated with the target. If the open price is $4,000 vs $3,000, there is no linear
relationship between the open price and the target. If the open price is higher, it doesn't mean the target price will be higher. In
stock price prediction, most of the relationships are nonlinear. If you can find a linear relationship, then everyone would be rich.
"""

model = RandomForestClassifier(n_estimators = 100, min_samples_split = 100, random_state = 1)
"""Here we initialize our model, where we pass in parameters:

n_estimators - the number of individual decision trees we want to train. The higher it is, generally the better the accuracy is up to
                a limit. The number also affects speed, but try higher than 100.

min_samples_split - helps protect against overfitting. Decision trees have a tendency to overfit if they build the tree too deeply. The
                     higher you set it, the less accurate the model will be, but the less it will overfit. May want to expirement to find
                     the optimal number.

random_state - a random forest has randomization built in. Setting a random state means that if we run the same model twice, the random
                numbers that are generated will be in a predictable sequence each time using the random seed of 1. So if we rerun the model
                twice, we will get the same results. It helps if you are updating or improving the model and when you want to make sure the
                model or something you did improved error vs. something random. 
"""

predictors = ["Close", "Volume", "Open", "High", "Low"] # the predictors list is what specific columns we'll use to predict our target.
                                                        # AKA, the input values fed into our model.
# Note: If we use the Tomorrow and/or Target column(s), it will train our model to use "future" data, which can't happen in the real world. 

from sklearn.metrics import precision_score # precision_score will analyze predictions with factual data

import pandas as pd

# combined = pd.concat([test["Target"], preds], axis = 1)
"""
We will plot our predictions, we'll combine our actual values with our predicted values using the pandas.concat(). axis = 1 will treat
each of the test["Target"] inputs as a column
"""


# combined.plot()

# plt.show()

# The orange line is our predictions and the blue line is what actually happened. 



# backtesting
def predict(train, test, predictors, model): # this function will wrap everything we just did into one function. 
    model.fit(train[predictors], train["Target"]) # fit the model using training predictors and target
    preds = model.predict(test[predictors]) # generate our predictions using the test predictors
    preds = pd.Series(preds, index = test.index, name = "Predictors") # combining model into series
    combined = pd.concat([test["Target"], preds], axis = 1) # combining real target data against predicted target data
    return combined # return combined dataframe 
"""
When you back test, you want to have a certain amount of data to train your first model. Every trading year has about 250 days. Start = 2500
says "take 10 years of data and train your first model with 10 years of data. The step = 250 means that we will be training a model for
about a year and then stepping into the next year. This will take the 10 years of data and predict values for the 11th year. Then we'll
take the first 11 years of data and predict values for the 12th year. Then we will take the first 12 years of data and predict values for
the 13th year and so on. This way we get predictions for a lot of different years and have more confidence in our model. 
"""

def backtest(data, model, predictors, start = 2500, step = 250):
    all_predictions = [] # list of each dataframe where each dataframe are predictions for a single year.

    for i in range(start, data.shape[0], step): # loop across our data year by year and make predictions all years except first 10 years. 
        train = data.iloc[0:i].copy() # split our training data from our test data, train is all years prior to current year.
        test = data.iloc[i:(i+step)].copy() # test set is the current year.
        predictions = predict(train, test, predictors, model) # use our predict function to generate our predictions 
        all_predictions.append(predictions) # append predictions for the given year
    return pd.concat(all_predictions) # concatenate all predictions together. Take a list of dataframes and combine into single dataframe. 


predictions = backtest(sp500, model, predictors) #backtest our sp500 data with the model and predictors we created earlier.

# accuracy = precision_score(predictions["Target"], predictions["Predictors"])
# print(accuracy)



horizons = [2,5,60,250,1000]
# """
# We will create a variety of rolling averages. If a human analyst is trying to predict if a stock will go up tomorrow, some of the numbers
# they might look at are if the stock price today is higher than it was last week, higher than it was three months ago, higher than a year
# ago or five years ago... They might use those inputs to determine if the stock will go up or down tomorrow. This is the information we will 
# give the algorithm.

# What these horizons are are horizons we want to look at for rolling means. We'll calculate the mean close price the last "2" days, the last
# trading week, "5" days, the last 3 months or so, "60" trading days, the last year, "250", and the last four years "1000". We'll find the
# ratio between todays closing price and the closing price in those periods which will help us know if the market went up by a lot?, because
# if so, it may be due for a downturn. Has the market gone down a lot?, because if so, it may be due for an upswing. This is the information
# we'll give the algorithm to help it make better predictions. 
# """

# new_predictors = [] # will hold some of the new columns we will create.

# for horizon in horizons: # loop through horizons and calculate a rolling average against the horizon.
#     rolling_averages = sp500.rolling(horizon).mean() # and take the mean

#     ratio_column = f"Close_Ratio_{horizon}" # create columns: Close_Ratio_2, Close_Ratio_5, Close_Ratio_60, etc.
#     sp500[ratio_column] = sp500["Close"] / rolling_averages["Close"] # add each column to our sp500 dataframe
#                                             # close price is divided by average, which gives us prices based on ratio for each column.

#     trend_column = f"Trend_{horizon}" # number of days in the past X days based on horizon number that the stock price actually went up.
#     sp500[trend_column] = sp500.shift(1).rolling(horizon).sum()["Target"] # shift trend column forward and find rolling sum of target.
#                             # this is sum of target X days before, based on the horizon number, where the stock price actually went up (1)

#     new_predictors += [ratio_column, trend_column]



# sp500



# """
# The NaN's in the first few days are a result of not enough rows prior to the current row to actually compute a rolling average. It is
# different for trend, because you can't include the current day, because if you did, you would be including todays target in that column
# which will give you leakage and making your algorithm look amazing, but won't work in the real world. 
# """


# sp500 = sp500.dropna() # get rid of extra rows using dropna() with the missing rows. 


# sp500


# """
# The data now starts in 1993, because of the close_ratio_1000 and the trend_1000 columns, which is about four years of data to compute
# those columns. 
# """


# model = RandomForestClassifier(n_estimators = 200, min_samples_split = 50, random_state = 1) # update model slightly



# # rewrite predict function slightly
# def predict(train, test, predictors, model): 
#     model.fit(train[predictors], train["Target"])
#     preds = model.predict_proba(test[predictors])[:,1] # now we want more control over the definition of target values 0 and 1, now use 
#                                                        # .predict_proba() will now return a probability that the row will be 0 or 1.
#                                                        # 2nd column
#     preds[preds >= .6] = 1 # set a custom threshold, which by default the threshold is .5, so if greater than 50% chance the model will
#     preds[preds < .6] = 0  # return that the price will go up, but we set to 60% so that model is more confident. It reduces the number
#                            # of days that the price will go up, but increase the chance that the model is accurate. We don't want risk. 
#     preds = pd.Series(preds, index = test.index, name = "Predictors")
#     combined = pd.concat([test["Target"], preds], axis = 1)
#     return combined


# predictions = backtest(sp500, model, new_predictors) # we got rid of "close", "open", "high", "low" "volume", "columns"
#                                                      # because they are absolute numbers. These are not super informative to the model.
#                                                      # the ratios are the more informative part.


# predictions["Predictors"].value_counts() # predictors now have different amount of days for both 0 and 1
#                                          # because we changed the threshold. We asked the model to be more confident.
#                                          # now we are buy stock on fewer days. But hopefully we will be more accurate on those days. 



# precision_score(predictions["Target"], predictions["Predictors"])



# """
# This score might not seem great, but it is actually pretty good. Especially when we are looking at times series data and we are just
# looking at historical prices of the index. This would actually make you money if you traded off of it from 1993 to the present. Would it
# be recommended to make trades based off this model? No. There are things that you can add to make it more accurate, but this is actaully
# a pretty good result given the data we had to work with and better than the baseline. 53% vs. 57% 

# If we want to continue with this model, there are exchanges open overnight. The S&P 500 only trades during US market hours, but there are
# other indecies around the world that open before the US market is open. So it might be worth looking at those prices and seeing if you
# can actually correlate them. If an index on the other side of the world is increasing, does that help predict the S&P 500? You can add
# in news so that includes articles that are coming out, general macroeconomic conditions like interest rates and inflation, etc. You can
# also think about adding in some key components of the S&P 500 like key stocks and key sectors. It's possible, for example, if tech is in
# a downturn, that six months later the S&P 500 will go down. Maybe it doesn't go down immediately. You can also try increasing the
# resolution. You can try looking at hourly data, minute-by-minute data, tick data, but not always the easiest or cheapest to get but if you
# can get that data, you can make more accurate predictions. You can build quite of bit on this model and get pretty far. 
# """
