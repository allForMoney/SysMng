/* Link like button */

import React from 'react';
import cx from 'classnames';
import styles from './LinkBtn.less';

function LinkBtn({ className, children, ...props }) {
  return (
    <button
      type="button"
      {...props}
      className={cx(styles.normal, className)}
    >
      {children}
    </button>
  );
}

export default LinkBtn;
