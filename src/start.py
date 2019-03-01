from flask import Flask, render_template

from hermes.api import initialize, save_images, get_screen

app = Flask(__name__)
images = []


def run():
    global images
    initialize()
    images = save_images()


@app.route("/")
def images():
    return render_template('slideshow.html', images=images, screen=get_screen())


if __name__ == "__main__":
    run()
    app.run(port=8000, host='0.0.0.0')
