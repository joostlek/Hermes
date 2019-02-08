import requests
from PIL import Image

from hermes import config
from hermes.error import ConfigFileNotPresent, PasswordNotAvailable, AuthenticationError

conf = config.load_config()
token = ''


def initialize():
    if conf.sections().__len__() == 0:
        raise ConfigFileNotPresent
    if not conf.has_option('GENERAL', 'password'):
        get_password()
    authenticate()


def get_password():
    global conf
    res = requests.request('POST', conf.get('GENERAL', 'url') + '/auth/screen/register',
                           json={'screenId': conf.get('GENERAL', 'screenid')})
    data = res.json()
    try:
        conf.set('GENERAL', 'password', data['password'])
        conf = config.write_config(conf)
    except KeyError:
        print('Please make the screen reauthenticate')
        raise PasswordNotAvailable


def authenticate():
    global token
    res = requests.post(conf.get('GENERAL', 'url') + '/auth/screen/signin',
                        json={'screenId': conf.get('GENERAL', 'screenid'), 'password': conf.get('GENERAL', 'password')})
    if res.status_code == 200:
        data = res.json()
        token = data['token']
    else:
        raise AuthenticationError(str(res.status_code))


def get_images():
    global token
    res = requests.get(conf.get('GENERAL', 'url') + '/screens/' + conf.get('GENERAL', 'screenid') + '/images',
                       headers={'Authorization': 'Bearer ' + token})
    data = res.json()
    return data


def get_raw_image(image_url):
    global token
    res = requests.get(conf.get('GENERAL', 'url') + '/files/' + image_url, headers={'Authorization': 'Bearer ' + token},
                       stream=True)
    return res.raw


def save_images():
    for image in get_images():
        img = Image.open(get_raw_image(image['url']))
        img.save('./' + str(image['id']) + '.png')
        img.close()


if __name__ == "__main__":
    initialize()
