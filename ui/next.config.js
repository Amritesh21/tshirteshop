/** @type {import('next').NextConfig} */
const nextConfig = {
  images : {
    unoptimized: true
  },
  reactStrictMode: false,
  /** TODO : remove below two properties in production */
  productionBrowserSourceMaps: true, // changes to show source maps in browser
  compress: false, // changes to show source maps in browser
}

module.exports = nextConfig