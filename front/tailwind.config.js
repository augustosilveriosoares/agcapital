/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{vue,js,ts,jsx,tsx}",
    "./node_modules/flowbite/**/*.js", // Certifique-se de que isso está correto
    "./node_modules/flowbite-vue/**/*.js" // Isso também
  ],
  theme: {
    extend: {},
  },
  plugins: [require('flowbite/plugin')] // Certifique-se de que o Flowbite está no plugins
}
