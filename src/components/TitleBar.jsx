/**
 * 通用标题栏组件
 * TODO: 样式有待调整
 */

import React from 'react';
import PropTypes from 'prop-types';
import cx from 'classnames';
import styles from './TitleBar.less';

function TitleBar(props) {
  const {
    className,
    children,
    title,
    afterTitle,
    size,
    level,
    ...otherProps
  } = props;

  return (
    <div
      className={cx(
        styles.normal,
        styles[size],
        level ? styles[level] : '',
        className)}
      {...otherProps}
    >
      <h2 className={styles.title}>
        {title}
      </h2>
      {children}
      {
        afterTitle && (
          <div className={styles.after}>
            {afterTitle}
          </div>
        )
      }
    </div>
  );
}

TitleBar.propTypes = {
  title: PropTypes.node,
  afterTitle: PropTypes.node,
  size: PropTypes.string,
  level: PropTypes.string,
};

TitleBar.defaultProps = {};

export default TitleBar;
