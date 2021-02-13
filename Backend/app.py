from flask import Flask, render_template, url_for, request
import os, pytesseract
from flask_uploads import UploadSet, configure_uploads, IMAGES
from PIL import Image

# path for current location
project_dir = os.path.dirname(os.path.abspath(__file__))

# path for tesseract module
pytesseract.pytesseract.tesseract_cmd = r"C:\Program Files\Tesseract-OCR\tesseract.exe"

# setup appllication
app = Flask(__name__,
            static_url_path='',static_folder='static', 
            template_folder='templates')   #referencing file folders

photos = UploadSet('photos', IMAGES)

app.config['DEBUG'] = True
app.config['UPLOAD_FOLDER'] = 'images'

# class for image to text
class GetText(object):
    def __init__(self, file):
        self.file = pytesseract.image_to_string(Image.open(project_dir + '/images/' + file))

# index route
@app.route('/', methods=['GET', 'POST'])
#function for the route
def home():
    if request.method == 'POST':
        if 'photo' not in request.files:
            return 'There is no photo in form'
        name = 'a' + '.jpg'
        photo = request.files['photo']
        path = os.path.join(app.config['UPLOAD_FOLDER'], name)
        photo.save(path)

        textObject = GetText(name)

        return textObject.file
    return render_template('index.html')

if __name__ == "__main__":
    app.run(debug=True)