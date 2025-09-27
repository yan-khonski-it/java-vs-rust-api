# 1. Build stage (inside Linux container)
FROM rust:1.85.0 AS builder
WORKDIR /usr/src/app
COPY . .
RUN cargo build --release

# 2. Runtime stage (tiny image with only binary)
FROM debian:bookworm-slim
WORKDIR /app
COPY --from=builder /usr/src/app/target/release/rust-app .
EXPOSE 8081
CMD ["./rust-app"]