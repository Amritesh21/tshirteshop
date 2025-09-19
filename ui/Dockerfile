FROM nginx:stable-perl
COPY out /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf
# COPY --from=builder /app/node_modules ./node_modules
# COPY --from=builder /app/public ./public
# COPY --from=builder /app/package.json ./package.json
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
