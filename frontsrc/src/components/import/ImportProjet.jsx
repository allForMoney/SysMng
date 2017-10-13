/**
 * 项目基本信息导入,用来导入项目信息
 */
import {
  Upload,
  Card,
  Button,
  Icon,
  message,
} from 'antd';

import React from 'react';
import FrameContent from '../common/FrameContent';

function ImportProjet() {
  const fileProps = {
    name: 'file',
    action: '/project/import',
    headers: {
      authorization: 'authorization-text',
    },
    onChange(info) {
      if (info.file.status !== 'uploading') {
        console.log(info.file, info.fileList);
      }
      if (info.file.status === 'done') {
        message.success(`${info.file.name} 上传成功`);
      } else if (info.file.status === 'error') {
        message.error(`${info.file.name} 上传失败`);
      }
    },
  };
  return (

    <Card title="项目基本信息导入">
      <a href="/common/templete/download?type=PROJECT" target="_blank">导入模板下载</a>
      <Upload {...fileProps}>
        <Button>
          <Icon type="upload" /> 点击上传并导入文件
          </Button>
      </Upload>

    </Card>

  );
}

export default ImportProjet;
