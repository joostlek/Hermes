import configparser


def load_config():
    conf = configparser.ConfigParser()
    conf.read('/hermes/config.ini')
    return conf


def write_config(config):
    config.write(open('/hermes/config.ini', 'w'))
    return config
