import os

class Config(object):
    SQLALCHEMY_DATABASE_URI = 'mysql+pymysql://root:coboman@/beyond_dancing'
    SQLALCHEMY_TRACK_MODIFICATIONS = False
    LOG_TO_STDOUT = os.environ.get('LOG_TO_STDOUT');
