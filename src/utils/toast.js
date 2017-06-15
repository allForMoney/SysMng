import { message } from 'antd';

/**
 * Toast Message
 * @param {String|Object} options
 * @param {String('info'|'loading'|'success'|'error'|'warning'|'warn')} options.type
 * @param {String} options.msg
 * @param {Number} [options.duration]
 */
export default function(options) {
  const isObj = Object.prototype.toString.call(options) === '[object Object]';
  const type = isObj && options.type || 'info';
  const msg = isObj && options.msg || options;
  const duration = isObj && options.duration || 2;

  if (!msg) {
    return;
  }

  message[type](msg, duration);
}
