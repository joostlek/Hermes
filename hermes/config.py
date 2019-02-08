import configparser


def load_config():
    conf = configparser.ConfigParser()
    conf.read('../config.ini')
    return conf


def write_config(config):
    config.write(open('../config.ini', 'w'))
    return config
