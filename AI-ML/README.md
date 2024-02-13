# Abnormal behavior detection with Raspberry Pi

This is a Raspberry Pi-based application with abnormal behavior detection function for seniors living alone.

## How it works

Abnormal behavior is detected through a Raspberry Pi connected to a webcam, and the detected images are uploaded to 'Firebase Storage' connected to the Raspberry Pi. When abnormal behavior is detected, a notification is sent to Android using ‘Realtime Database’ and ‘Firebase Cloud Message’, and a photo capturing the abnormal behavior can be displayed on Android.

Therefore, when abnormal behavior is detected, you can check a notification message and an image capturing the abnormal behavior on Android.

## Equipment used (Hardware)

- Raspberry Pi 4 (4GB)
- Webcam

## Tools 

- Putty
- RealVNC Viewer 

