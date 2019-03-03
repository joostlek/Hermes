import os

import requests
from PIL import Image
from hermes import config
from hermes.error import ConfigFileNotPresent, PasswordNotAvailable, AuthenticationError, MethodError
from requests.auth import HTTPBasicAuth

conf = config.load_config()
jar = None
screen = None


def initialize():
    if conf.sections().__len__() == 0:
        raise ConfigFileNotPresent
    if not conf.has_option('GENERAL', 'password'):
        get_password()
    authenticate()
    get_screen_information()


def get_password():
    global conf
    res = requests.request('POST', conf.get('GENERAL', 'url') + '/api/screen/register',
                           json={'screenId': conf.get('GENERAL', 'screenid')})
    data = res.json()
    try:
        print(data)
        conf.set('GENERAL', 'password', data['password'])
        conf = config.write_config(conf)
    except KeyError:
        print('Please make the screen reauthenticate')
        raise PasswordNotAvailable


def authenticate():
    global jar
    res = requests.get(conf.get('GENERAL', 'url') + '/me',
                       auth=HTTPBasicAuth(str(conf.get('GENERAL', 'screenid')), conf.get('GENERAL', 'password')))
    if res.status_code == 200:
        jar = res.cookies
    else:
        raise AuthenticationError(str(res.status_code))


def do_authenticated_request(method, endpoint, json=None, stream=False):
    global jar
    if method not in ['POST', 'GET', 'PUT', 'DELETE']:
        raise MethodError(method)
    res = requests.request(method, conf.get('GENERAL', 'url') + endpoint, json=json, stream=stream, cookies=jar)
    if res.status_code == 401:
        authenticate()
        return do_authenticated_request(method, endpoint, json=json, stream=stream)
    return res


def get_screen_information():
    global screen
    response = do_authenticated_request('GET', '/screens/' + conf.get('GENERAL', 'screenid'))
    screen = response.json()


def get_images():
    res = do_authenticated_request('GET', '/screens/' + conf.get('GENERAL', 'screenid') + '/images')
    data = res.json()
    return data


def get_raw_image(image_url):
    res = do_authenticated_request('GET', '/files/' + image_url, stream=True)
    return res.raw


def check_folder():
    if not os.path.exists('../static/images'):
        os.makedirs('../static/images')


def save_images():
    check_folder()
    images = get_images()
    print(os.listdir())
    print(os.listdir('./../static/images/'))
    for image in images:
        img = Image.open(get_raw_image(image['url']))
        img.save('./../static/images/' + str(image['id']) + '.png')
        img.close()
    return images


def get_token():
    global jar
    return jar['SESSION']


def get_screen():
    global screen
    return screen


if __name__ == "__main__":
    initialize()
