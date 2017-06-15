/**
 * yield 队列的延迟
 * @author minhui.lmh
 * @since 2017/4/10.
 */

export default function delay(time) {
  const timerId = `delay-${new Date().getTime()}`;
  console.time(timerId); // eslint-disable-line
  return new Promise((resolve) => {
    setTimeout(() => {
      resolve();
      console.timeEnd(timerId); // eslint-disable-line
    }, time);
  });
}
