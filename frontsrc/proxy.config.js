const fs = require('fs');
const path = require('path');

const rules = {
  '/index.js': '/index.js'
};

require('fs').readdirSync(require('path').join(`${__dirname}/mock`))
  .forEach((file) => {
    // eslint-disable-next-line
    Object.assign(rules, require(`./mock/${file}`));
  });

module.exports = rules;
