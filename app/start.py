import configparser

import requests
from PIL import Image

config_file = '../config.ini'


def load_config():
    conf = configparser.ConfigParser()
    conf.read(config_file)
    return conf


config = load_config()
general = config['GENERAL']


def run():
    if not general.get('password'):
        register()
    jwt = get_token(config.get('GENERAL', 'password'), config.get('GENERAL', 'screenid'))
    for image in get_images(jwt, config.get('GENERAL', 'screenid')):
        try:
            img = Image.open(get_raw_image(jwt, image['url']))
            img.save(str(image['id']) + '.png')
        except OSError:
            print('Image ' + str(image['id']) + ' could not be downloaded!')


def register():
    res = requests.request('POST', general.get('url') + '/auth/screen/register',
                           json={'screenId': general.get('screenid')})
    data = res.json()
    if data['password']:
        config.set('GENERAL', 'password', data['password'])
        config.write(open(config_file, 'w'))
    else:
        print('Please make the screen reauthenticate')
        exit()


def get_token(password, screen_id):
    res = requests.post(general.get('url') + '/auth/screen/signin', json={'screenId': screen_id, 'password': password})
    data = res.json()
    print(data)
    return data['token']


def get_images(jwt, screen_id):
    res = requests.get(general.get('url') + '/screens/' + str(screen_id) + '/images',
                       headers={'Authorization': 'Bearer ' + jwt})
    data = res.json()
    print(data)
    return data


def get_raw_image(jwt, image_url):
    res = requests.get(general.get('url') + '/files/' + image_url, headers={'Authorization': 'Bearer ' + jwt},
                       stream=True)
    return res.raw


if __name__ == "__main__":
    run()
