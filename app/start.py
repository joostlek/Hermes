import requests

url = "http://localhost:8080"


def run():
    screen_id = 1
    password = register(screen_id)
    jwt = get_token(password, screen_id)
    for image in get_images(jwt, screen_id):
        print(image['name'])


def register(screen_id):
    res = requests.request('POST', url + '/auth/screen/register', json={'screenId': screen_id})
    data = res.json()
    print(data)
    return data['password']


def get_token(password, screen_id):
    res = requests.post(url + '/auth/screen/signin', json={'screenId': screen_id, 'password': password})
    data = res.json()
    print(data)
    return data['token']


def get_images(jwt, screen_id):
    res = requests.get(url + '/screens/' + str(screen_id) + '/images', headers={'Authorization': 'Bearer ' + jwt})
    data = res.json()
    print(data)
    return data


if __name__ == "__main__":
    run()
