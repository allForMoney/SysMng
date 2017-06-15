module.exports = {
  'post /platform/uploadAttachment.json': function(req, res) {
    setTimeout(() => {
      res.json({
        stat: 'ok',
        msg: '处理成功',
        resp: {
          url: 'https://zos.alipayobjects.com/rmsportal/jkjgkEfvpUPVyRjUImniVslZfWPnJuuZ.png'
        }
      });
    }, 1000);
  },

  'get /platform/queryMcc.json': function(req, res) {
    setTimeout(() => {
      res.json({
        stat: 'ok',
        msg: '处理成功',
        mccCategories:[{
          categoryCode:'301',
          categoryDesc:'三级分类1',
          needUploadFiles:['qualification'],
          
          },{
          categoryCode:'302',
          categoryDesc:'三级分类2'
          }]
      });
    }, 1000);
  },

  'get /platform/getBaseInfo.json': function(req, res) {
    setTimeout(() => {
      res.json({
        stat: 'ok',
        msg: '处理成功',
        openForm: {},
        mccCategories1:[{
          categoryCode:'201',
          categoryDesc:'二级1',
          parentCode: '101',
          },{
          categoryCode:'202',
          categoryDesc:'二级分类2',
          parentCode: '102'
          }]
      });
    }, 1000);
  },
};
