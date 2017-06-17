import React, { PropTypes } from 'react';
import { connect } from 'dva';
import { Steps } from 'antd';
import SignStep1 from '../components/sign/SignStep1';
import SignStep2 from '../components/sign/SignStep2';
import SignStep3 from '../components/sign/SignStep3';
import { SIGN_NAMESPACE, STEPCONFIG } from '../configs/signConfigs';
import styles from '../less/sign.less';
import FrameContent from '../components/common/FrameContent';

const Step = Steps.Step;

function Sign(props) {
  return (
    <FrameContent location={props.location} {...props} >
      <div className={styles.container}>
        <Steps current={props.currentStep}>
          {STEPCONFIG.map(item => <Step key={item.title} title={item.title} />)}
        </Steps>
        <div className={styles.content}>
          {
              props.currentStep === 0
              &&
              <SignStep1 {...props} />
          }
          {
              props.currentStep === 1
              &&
              <SignStep2 {...props} />
          }
          {
              props.currentStep === 2
              &&
              <SignStep3 {...props} />
          }
        </div>
      </div>
    </FrameContent>
  );
}

Sign.propTypes = {
  currentStep: PropTypes.number,
};

function mapStateToProps(state) {
  const {
    currentStep,
    certifiedName,
    isAgreed,
    mainBody,
    isPerson,
    privateLicenseId, // 营业执照id
    privateLicenseURL, // 个人营业执照url
    privateLicenseName, // 个人营业执照名字
    merchantCompanyName,
    editName,
    editPhone,
    editEmail,
    name,
    email,
    mobile,
    newConcatName,
    newEmail,
    newPhoneNum,

    logoId,
    logoURL,
    backgroundURL,
    publicName,
    publicDesc,
    
    mccIndustry1,
    mccIndustry2,
    ownIntellectualId, // 知识产权证明
    ownIntellectualUrl,
    authorizationId, // 授权书
    authorizationURL,
    authorizationName,
    qualificationId1, // 个人资质证明
    qualificationURL1,
    qualificationName1,

    publicNameAvailable,

    mccCategories1,
    mccCategories2,

    showAddHeadImg,
    showAddBgImg,

    logoFileList,
    bgFileList,

    showLogoError,
    showBgError,
    loading,
  } = state[SIGN_NAMESPACE];
  return {
    certifiedName,
    currentStep,
    isAgreed,
    mainBody,
    isPerson,
    privateLicenseId, // 营业执照id
    privateLicenseURL, // 个人营业执照url
    privateLicenseName, // 个人营业执照名字
    merchantCompanyName,
    editName,
    editPhone,
    editEmail,
    name,
    email,
    mobile,
    newConcatName,
    newEmail,
    newPhoneNum,
    logoId,
    logoURL,
    backgroundURL,
    publicName,
    publicDesc,
    
    mccIndustry1,
    mccIndustry2,
    ownIntellectualId, // 知识产权证明
    ownIntellectualUrl,
    authorizationId, // 授权书
    authorizationURL,
    authorizationName,
    qualificationId1, // 个人资质证明
    qualificationURL1,
    qualificationName1,

    publicNameAvailable,
    mccCategories1,
    mccCategories2,

    showAddHeadImg,
    showAddBgImg,

    logoFileList,
    bgFileList,
    showLogoError,
    showBgError,
    loading,
  };
}

export default connect(mapStateToProps)(Sign);
