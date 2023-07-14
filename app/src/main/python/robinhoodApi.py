import robin_stocks as r
import os
import time
import random

def getLoginSession(username, password, mfa, sessionName):
  try:
    thisSession = r.robinhood.authentication.login(username=username, password=password, mfa_code=mfa, store_session=True, pickle_name=sessionName)
    return True
  except Exception as e:
    print(str(e))
  return False

def getCashBalance():
  try:
    accountNumber = r.robinhood.profiles.load_account_profile()
  except Exception:
    print("Account Not Found or Logged out of session!")
    return "~"
  return r.robinhood.profiles.load_account_profile(info="cash")

def isActiveSession(sessionName):
  try:
    test = r.robinhood.authentication.login(username=" ", password=" ", pickle_name=sessionName)
    thisSession = test
  except Exception:
    print("Session NOT found")
    return False
  return True

def logoutOfSession():

  try:
    r.robinhood.logout()
  except Exception:
    return False

  return True