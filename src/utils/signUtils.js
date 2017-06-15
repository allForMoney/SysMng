export default function getCookie(key) {
//   const m = new RegExp('\\b' + key + '\\=([^;]+)').exec(document.cookie);
  const m = new RegExp(`\\b${key}\\=([^;]+)`).exec(document.cookie);
  return m ? encodeURIComponent(m[1]) : '';
}

export function getResultFromHtml(html) {
  let data = null;
  if (html.indexOf('</script>') > 0) {
    data = html.substring((html.indexOf('</script>') + 9), html.indexOf('</html>'));
  }
  try {
    data = $.parseJSON(data);
  } catch (e) {}
  return data;
}

export function decodeUrl(url) {
  return url.replace(/&amp%3B/g, '&').replace(/&amp;/g, '&');
}
