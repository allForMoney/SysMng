export const SIGN_NAMESPACE = 'SIGN_NAMESPACE';

/** API 接口 */
// 获取基本信息
export const API_GET_BASEINFO = '/platform/getBaseInfo.json';
// 发送验证码
export const API_SEND_SMSCODE = '/platform/mobileAckCodeAndSend.json';
// 校验验证码
export const API_CHECK_SMSCODE = '/platform/verifyAckCode.json';
// 校验生活号名称唯一性
export const API_CHECK_PUBLICNAME = '/platform/judgePublicName.json';
// 获取行业三级分类
export const API_GET_CLASSIFY = '/platform/queryMcc.json';
// 上传原始图片 已废弃
export const API_UPLOAD_PICS = '/home/uploadOriginalPic.htm';
// 裁剪图片
export const API_JCROP_PIC = '/platform/uploadPic.json';
// 上传文件
export const API_UPLOAD_FILES = '/project/uploadProject';
// 保存,即提交表单接口
export const API_SUBMIT = '/platform/open.htm';
/** ---API 接口结束 --- */
// 步骤配置
export const STEPCONFIG = [
  {
    title: '第一步'
  }, {
    title: '第二步'
  }, {
    title: '第三步'
  }
];
// 发送短信验证码的时间间隔(默认应该是 60s, 即 60000)
export const ASKCODEINTERVAL = 1000;

/** 项目中使用到的正则 */
// 手机验证码 正则
export const ASKCODEREG = /^[0-9]*$/;
// 电话号码验证正则
export const PHONENUMREG = /^1[34578]{1}\d{9}$/;
// 电子邮箱 正则
export const EMAILREG = /^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$/;
// 联系人姓名正则
export const CONCATNAMEREG = /^\S+$/;
// 公众号名称 正则
export const PUBLICNAMEREG = /^[-\s\w\.\(\)\'\u4e00-\u9fa5]+$/;

/** 系统常量 */
export const FILE_NEED_QULIFI = 'qualification';
export const FILE_NEED_OWNINTELL = 'ownIntellectua';
export const FILE_NEED_AUTH = 'authorization';

export const MAINBODY_PRIVETE = 'private';
