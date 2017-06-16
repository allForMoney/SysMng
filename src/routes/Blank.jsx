import React from 'react';
import { Card } from 'antd';
import FrameContent from '../components/common/FrameContent';

function Blank() {
  return (
    <FrameContent>
      <Card style={{ width: '100%', height: '100%', fontSize: 32 }}>
        <p>内容建设中,敬请期待</p>
      </Card>
    </FrameContent>
  );
}

export default Blank;
