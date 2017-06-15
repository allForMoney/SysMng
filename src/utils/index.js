export debug from './debug';
export toast from './toast';
export getQueryParam from './getQueryParam';

if (!String.prototype.trim) {
  // eslint-disable-next-line
  String.prototype.trim = function() {
    return this.replace(/^[\s\uFEFF\xA0]+|[\s\uFEFF\xA0]+$/g, '');
  };
}

// 中文算两个长度
export function getMixedStringLength(str) {
  return str ? str.replace(/[\u0391-\uFFE5]/g, 'mh').length : 0;
}

export function trim(str) {
  // return str.replace(/^\s+|\s+$/g, '');
  str = str.replace(/^\s\s*/, '');
  const ws = /\s/;
  let i = str.length;
  // eslint-disable-next-line
  while (ws.test(str.charAt(--i)));
  return str.slice(0, i + 1);
}

export function encodeHtml(unsafe) {
  return unsafe
    .replace(/&/g, '&amp;')
    .replace(/</g, '&lt;')
    .replace(/>/g, '&gt;')
    .replace(/"/g, '&quot;')
    .replace(/'/g, '&#039;');
}

export function decodeHtml(encode) {
  return encode
    .replace(/&mdash;/g, '— ')
    .replace(/&ldquo;/g, '“')
    .replace(/&rdquo;/g, '”')
    .replace(/&lsquo;/g, '‘')
    .replace(/&rsquo;/g, '’')
    .replace(/&quot;/g, '"')
    .replace(/&#39;/g, '\'')
    .replace(/&lt;/g, '<')
    .replace(/&gt;/g, '>')
    .replace(/&amp;/g, '&');
}

export function getCookie(cookieName) {
  const name = `${cookieName}=`;
  const cookies = document.cookie.split(';');
  for (let i = 0; i < cookies.length; i++) {
    const cookie = cookies[i].trim();
    if (cookie.indexOf(name) === 0) {
      return cookie.substring(name.length, cookie.length);
    }
  }

  return '';
}

export function getCToken() {
  return getCookie('ctoken');
}

export function isEmptyObject(obj) {
  if (!obj) {
    return true;
  }

  if (Object.keys) {
    return Object.keys(obj).length === 0 && obj.constructor === Object;
  }

  // eslint-disable-next-line
  for (const prop in obj) {
    if (Object.prototype.hasOwnProperty.call(obj, prop)) {
      return false;
    }
  }

  return JSON.stringify(obj) === JSON.stringify({});
}

export function isArray(obj) {
  if (Array.isArray) {
    return Array.isArray(obj);
  }

  return Object.prototype.toString.call(obj) === '[object Array]';
}

export function localStorageDebug(key, value) {
  try {
    if (window.localStorage.getItem('tinyappDebug')) {
      window.localStorage.setItem(`${key}_${Date.now()}`, value);
    }
  } catch (e) {}
}

export function getQueryString(name) {
  const reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
  const r = window.location.search.substr(1).match(reg);
  if(r!=null)return  unescape(r[2]); return null;
}
