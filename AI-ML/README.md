# Abnormal behavior detection with Raspberry Pi

This is a Raspberry Pi-based application with abnormal behavior detection function for seniors living alone.

## How it works

Abnormal behavior is detected through a Raspberry Pi connected to a webcam, and the detected images are uploaded to 'Firebase Storage' connected to the Raspberry Pi. When abnormal behavior is detected, a notification is sent to Android using ‘Realtime Database’ and ‘Firebase Cloud Message’, and a photo capturing the abnormal behavior can be displayed on Android.

Therefore, when abnormal behavior is detected, you can check a notification message and an image capturing the abnormal behavior on Android.

## Equipment used (Hardware)
Information
<table>
  <tr>
    <td>
    Raspberry Pi 4 (4GB)
    </td>
    <td>
       Webcam - Logitech Pro Converter C922
    </td>
  </tr>
  <tr>
  <td>  <img width="300" alt="image" src="https://github.com/GDSC-SWU/2024-Elderus-SolutionChallenge/assets/81478444/dbc4e27b-fff2-4c43-a21a-0dc919d47f67"></td>
    <td>
      <img width="281" alt="image" src="https://github.com/GDSC-SWU/2024-Elderus-SolutionChallenge/assets/81478444/992900a4-b9f2-4f8f-826d-996a7d93df3d">
    </td>
  </tr>
</table>

## Tools 

- Putty
- RealVNC Viewer

## Step

1. Turn on the raspberry pie connected to the camera and clone yolov5. 

2. Put detect.py and best.pt in yolov5 folder. 

3. Write the following commands.

```

python detect.py --weights best.py --source 0 --conf 0.7

```
  
## Test Video 


https://github.com/GDSC-SWU/2024-Elderus-SolutionChallenge/assets/81478444/440d1526-394c-4a68-a88e-8d6680a79f45

