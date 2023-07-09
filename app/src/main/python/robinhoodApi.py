import robin_stocks as r

def getCashBalance(username, password, mfa):
  r.robinhood.authentication.login(username=username, password=password, store_session=True)
  accountNumber = r.robinhood.profiles.load_account_profile(info="account_number")
  return r.robinhood.profiles.load_account_profile(info="cash")