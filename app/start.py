from flask import Flask

from hermes.api import initialize, save_images

app = Flask(__name__)


def run():
    initialize()
    save_images()


@app.route("/")
def images():
    return "kek"


if __name__ == "__main__":
    run()
    app.run(port=8082)
