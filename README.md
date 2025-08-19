# Guest Lobby Service (No DB, No Auth)

Backend sederhana untuk membuat *room* dengan kode 4 huruf dan join sebagai tamu — cocok untuk dipakai bareng frontend (mis. Netlify).

## Endpoint
- `GET /guest/health` → `{"status":"ok"}`
- `POST /guest/room?name=HOST` → buat room baru (return kode)
- `POST /guest/room/{CODE}/join?name=NAMA` → join ke room
- `GET /guest/room/{CODE}` → detail room dan daftar pemain

CORS diaktifkan (`*`). Port default `34172` (bisa override via env `PORT`).

## Cara Jalan (Lokal / Codespaces)

```bash
# 1) Build
mvn -DskipTests package

# 2) Run (gunakan port default 34172)
java -jar target/guest-lobby-1.0.0.jar

# 3) Cek
curl http://localhost:34172/guest/health
```

Contoh:
```bash
# Buat room
curl -X POST "http://localhost:34172/guest/room?name=HostA"

# Join (gantikan ABCD dengan kode dari hasil create)
curl -X POST "http://localhost:34172/guest/room/ABCD/join?name=Pemain1"

# Lihat room
curl "http://localhost:34172/guest/room/ABCD"
```

## Deploy (opsional)

- **GitHub Codespaces:** Jalankan perintah di atas, lalu *forward* port 34172 dan set ke **Public**.
- **Docker:**
  ```bash
  docker build -t guest-lobby:1.0.0 .
  docker run -p 34172:34172 guest-lobby:1.0.0
  ```
- **Render / Railway / Fly / Koyeb:** Gunakan `mvn -DskipTests package` lalu jalankan `java -jar target/guest-lobby-1.0.0.jar`, pastikan env `PORT` diisi jika platform mengharuskan.

## Catatan
Data room tersimpan di memory — restart server = data hilang. Untuk produksi, ganti ke penyimpanan persisten (Redis/DB).