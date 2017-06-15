/**
 * 没有内容时显示的占位提示组件
 */
import React from 'react';
import cx from 'classnames';
import { Icon } from 'antd'
import styles from './ContentPlaceholder.less';

function ContentPlaceHolder({ className, children, ...props }) {
  return (
    <div
      {...props}
      className={cx(styles.normal, className)}
    >
      <Icon type="inbox" className={styles.icon} />
      {children ? (
        <div className={styles.content}>
          {children}
        </div>
      ) : null}
    </div>
  );
}

export default ContentPlaceHolder;
