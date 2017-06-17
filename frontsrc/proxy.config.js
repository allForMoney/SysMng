const fs = require('fs');
const path = require('path');

const rules = {
  
};

require('fs').readdirSync(require('path').join(`${__dirname}/mock`))
  .forEach((file) => {
    // eslint-disable-next-line
    Object.assign(rules, require(`./mock/${file}`));
  });

module.exports = rules;
