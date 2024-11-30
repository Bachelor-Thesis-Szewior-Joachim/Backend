FROM python:3.9-slim

WORKDIR /app

# Install curl and other dependencies
RUN apt-get update && apt-get install -y --no-install-recommends \
    curl \
    && apt-get clean && rm -rf /var/lib/apt/lists/*

# Copy requirements and install dependencies
COPY requirements.txt .
COPY models /app/models
RUN pip install --no-cache-dir -r requirements.txt

# Copy service files
COPY . .

# Expose port and run the service
EXPOSE 5000
CMD ["python", "model_service.py"]
