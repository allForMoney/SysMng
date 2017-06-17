module.exports = {
  "parser": "babel-eslint",
  "extends": "eslint-config-airbnb",
  "globals": {
    "AlipayJSBridge": false,
    "document": false,
    "window": false,
    "XMLHttpRequest": false
  },
  "rules": {
    // http://eslint.org/docs/rules/#best-practices
    "consistent-return": [0],
    "no-param-reassign": [1],
    "no-unused-expressions": ["error", { "allowShortCircuit": true }],
    "array-callback-return": [1],

    // Code style
    "camelcase": [1],
    "no-mixed-operators": [1],
    "no-plusplus": [1],
    "no-nested-ternary": [1],
    "generator-star-spacing": ["warn", {"before": false, "after": true}],
    "comma-dangle": ["error", {
      "arrays": "only-multiline",
      "objects": "only-multiline",
      "imports": "only-multiline",
      // "exports": "never",
      "functions": "ignore"
    }],

    // Possible Errors
    "no-empty": ["error", { "allowEmptyCatch": true }],

    "space-before-function-paren": ["error", "never"],
    "react/forbid-prop-types": [0],
    "react/no-multi-comp": [0],
    "react/jsx-no-bind": [0],
    "react/prefer-stateless-function": [1],
    "react/require-default-props": [0],
    "react/jsx-filename-extension": [1, { "extensions": [".js", ".jsx"] }],
    "react/prop-types":["warn", {
      "ignore": [
        "location",
        "params",
        "className",
        "children",
        "dispatch",
        "history",
      ]
    }],
    "react/no-array-index-key": [1],
    "import/first": ["warn", "DISABLE-absolute-first"],

    // common.js
    "global-require": ["off"]
  },
  "ecmaFeatures": {
    "experimentalObjectRestSpread": true,
    "jsx": true
  }
};
