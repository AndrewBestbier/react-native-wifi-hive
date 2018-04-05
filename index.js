import {
  NativeModules,
} from 'react-native';

export function getWifiList() {
  return new Promise((resolve, reject) => {
      NativeModules.RNWifi.getWifiList((err, list) => {    
        if (err) {
          return reject(err);
        }
        resolve(list);
      });
  });
}