from flask import Flask, request, jsonify
from tensorflow.keras.models import load_model
import numpy as np

# Initialize Flask app
app = Flask(__name__)

# Load models
models = {
    "ethereum": load_model("ethereum_model.h5"),
    "bitcoin": load_model("bitcoin_model.h5"),
    "solana": load_model("solana_model.h5"),
}

# Prediction route
@app.route('/predict', methods=['POST'])
def predict():
    data = request.json
    day_input = data.get('day')  # Input for the models, e.g., [[value1, value2, ...]]

    if not day_input:
        return jsonify({"error": "Invalid input data"}), 400

    predictions = {}
    for model_name, model in models.items():
        try:
            # Reshape input for prediction
            prediction = model.predict(np.array(day_input).reshape(1, -1))
            predictions[model_name] = prediction.tolist()
        except Exception as e:
            predictions[model_name] = {"error": str(e)}

    return jsonify(predictions)

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000)
