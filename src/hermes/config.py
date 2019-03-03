from configparser import ConfigParser


def load_config(path: str) -> ConfigParser:
    conf = ConfigParser()
    conf.read(path)
    return conf


def write_config(path: str, config: ConfigParser) -> ConfigParser:
    config.write(open(path, 'w'))
    return config
