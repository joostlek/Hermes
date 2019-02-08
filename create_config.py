import configparser
import os


def create():
    if check_current_file_exists():
        print('Your current config file will be lost, is that OK?')
        ok = ''
        while ok != 'y' and ok != 'Y' and ok != 'n' and ok != 'N':
            ok = input('(Y/n)')
        if ok == 'N' or ok == 'n':
            return
    config = configparser.ConfigParser()
    screen_id = input('Screen id: ')
    config['GENERAL'] = {}
    config['GENERAL']['ScreenId'] = screen_id
    url = input('Server url [http://localhost:8080]: ')
    if url == '':
        url = 'http://localhost:8080'
    config['GENERAL']['url'] = url
    config.write(open('./config.ini', 'w'))


def check_current_file_exists():
    return os.path.isfile('./config.ini')


if __name__ == "__main__":
    create()
