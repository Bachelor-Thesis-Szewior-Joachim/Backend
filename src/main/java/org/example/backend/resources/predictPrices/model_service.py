from flask import Flask, request, jsonify
from tensorflow.keras.models import load_model
import numpy as np
from datetime import datetime  # Import for date preprocessing

# Helper function to preprocess the date
def preprocess_date(date_string):
    # Parse the date string
    date = datetime.strptime(date_string, "%Y-%m-%d")

    # Option 1: Day of the year
    day_of_year = date.timetuple().tm_yday

    # Option 2: Separate features
    day = date.day
    month = date.month
    year = date.year

    # Option 3: Unix timestamp
    timestamp = int(date.timestamp())

    # Choose the format your model expects (example: day of the year)
    return [day_of_year]

# Initialize Flask app
app = Flask(__name__)

# Load models
models = {
    "ethereum": load_model("models/ethereum_model.h5"),
    "bitcoin": load_model("models/bitcoin_model.h5"),
    "solana": load_model("models/solana_model.h5"),
}

# Prediction route
@app.route('/predict', methods=['POST'])
def predict():
    data = request.json
    date_input = data.get('date')  # Expecting input like "2024-11-30"

    if not date_input:
        return jsonify({"error": "Invalid input data"}), 400

    # Preprocess the date
    preprocessed_input = preprocess_date(date_input)

    predictions = {}
    for model_name, model in models.items():
        try:
            # Reshape input for prediction
            prediction = model.predict(np.array(preprocessed_input).reshape(1, -1))
            predictions[model_name] = prediction.tolist()
        except Exception as e:
            predictions[model_name] = {"error": str(e)}

    return jsonify(predictions)

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000)
