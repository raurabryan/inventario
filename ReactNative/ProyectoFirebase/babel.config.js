module.exports = function(api) {
    api.cache(true);
    return {
        presets: ['babel-preset-expo'],
        plugins: [
            [
                "module-resolver",
                {
                    root: ["./src"], // Specify the root directory of your project
                    alias: {
                        // Define your aliases here
                        components: "./src/components",
                        screens: "./src/screens",
                        utils: "./src/utils",
                        // Add more aliases as needed
                    },
                    extensions:['.tsx','.ts','.js','.json']
                }
            ],
            'react-native-reanimated/plugin'
        ],
    };
  };