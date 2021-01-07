package com.example.tokokuid.core

import com.example.tokokuid.core.modelpresentation.Courier
import com.example.tokokuid.core.modelpresentation.Item
import com.example.tokokuid.core.modelpresentation.Province
import com.example.tokokuid.core.modelpresentation.TypeSend

object DataDummy {
    fun getProvince(): ArrayList<Province> = arrayListOf(
        Province("Sumatera Barat", 1),
        Province("Jakarta", 2),
        Province("Sumatera Utara", 3),
        Province("Aceh", 4),
        Province("Riau", 5),
        Province("Jambi", 6),
        Province("Gorontalo", 7),
        Province("Nusa Tenggara Barat", 8),
        Province("Nusa Tenggara Timur", 9),
        Province("Sulawesi Barat", 10),
        Province("Sulawesi Selatan", 11),
        Province("Sulawesi Tenggara", 12),
        Province("Sulawesi Tengah", 13),
        Province("Sulawesi Utara", 14),
        Province("Kalimatan Barat", 15),
        Province("Kalimantan Timur", 16),
    )

    fun getCourier(): ArrayList<Courier> {
        val jne =
            arrayListOf(
                TypeSend(
                    "Yakin Esok Sampai",
                    30000
                ), TypeSend("Reguler", 35000)
            )
        val tiki = arrayListOf(
            TypeSend(
                "Esok Sampai",
                40000
            ), TypeSend("Reg", 35000)
        )
        return arrayListOf(
            Courier(
                "JNE",
                jne
            ), Courier("Tiki", tiki)
        )
    }

    fun getClothes(): ArrayList<Item> = arrayListOf(
        Item(
            "Baju Polo Merah",
            35000,
            560,
            R.drawable.gambar_1,
            "Deskripsi POLO MERAH CABE POLO SHIRT PRIA / MEN / COWOK POLOS - POLO MERAH CABE, L\n" +
                    "Ketersediaan stok size M L XL\n" +
                    "\n" +
                    "NB : POLO KAMI DIPRODUKSI DARI BANYAK PABRIKAN SEHINGGA DETAIL BARANG TIDAK AKAN SAMA / TIDAK MIRIP DARI WARNA SATU KE WARNA LAIN. TAPI MASIH DALAM BATAS WAJAR , MOHON DIMAKLUMI\n" +
                    "\n" +
                    "Polo Shirt Polos/ Kaos Kerah Polos Model Cowok\n" +
                    "- Bisa Pesen Satuan/Ratusan\n" +
                    "- Bahan Lacoste PE Double Knit ( Bahan Tebal )\n" +
                    "- Buatan lokal indonesia non branded\n" +
                    "- Bisa Order Warna Dan Ukuran Yang Berbeda beda\n" +
                    "- Kaos Di Pakai Nyaman\n" +
                    "- Bisa di design dengan printing / cutting dari kami langsung\n" +
                    "\n" +
                    "(Cocok Untuk Di pakai Sehari-Hari, Kuliah, Komunitas, Family, Seragam)\n" +
                    "\n" +
                    "- Ukuran S : 63 cm x 45 cm (Panjang x Lebar)\n" +
                    "- Ukuran M : 65 cm x 48 cm (Panjang x Lebar)\n" +
                    "- Ukuran L : 68 cm x 50 cm (Panjang x Lebar)\n" +
                    "- Ukuran XL : 72 cm x 52 cm (Panjang x Lebar)\n" +
                    "\n" +
                    "Untuk warna dan size polo shirt yang dipesan dapat di tulis didalam keterangan saat order, jika tidak maka pesanan akan kami kirim warna secara acak."
        ),
        Item(
            "Sweater Hoodie Polos Putih",
            150000,
            1200,
            R.drawable.gambar_2,
            "Deskripsi Jaket Sweater Polos Hoodie Jumper Putih Polos Pria Wanita\n" +
                    "SWEATER LOKAL, KUALITAS INTERNATIONAL :)\n" +
                    "\n" +
                    "STOK banyak. Selalu READY STOCK.\n" +
                    "Ini memudahkan anda untuk langsung Order tanpa harus menanyakan stock terrlebih dulu kepada kami.\n" +
                    "\n" +
                    "Tersedia warna\n" +
                    "1. Biru Navy,\n" +
                    "2, Merah Marun,\n" +
                    "3. Hitam,\n" +
                    "4. Merah Terang,\n" +
                    "5. Abu Misty Muda,\n" +
                    "6. Abu Misty Tua,\n" +
                    "7. Biru Benhur,\n" +
                    "8. Hijau Army\n" +
                    "9. Pink Fanta\n" +
                    "10. Turkish\n" +
                    "11. Coklat\n" +
                    "12. Putih\n" +
                    "13. Tosca\n" +
                    "14. Hijau Botol\n" +
                    "15. Pink Baby\n" +
                    "16. Kuning Baby\n" +
                    "17. Mocca\n" +
                    "18. Hijau Fuji\n" +
                    "\n" +
                    "Lihat penampakan warnya cek di etalase produk hoodie zipper\n" +
                    "\n" +
                    "Kami menggunakan bahan dasar cotton fleece untuk memberikan kelembutan bahan saat menyentuh kulit anda dengan standar kualitas distro tentunya.\n" +
                    "\n" +
                    "Di jahit oleh penjahit yang sudah profesional selama puluhan tahun dibidangnya. kami mendahulukan kualitas produk kami untuk kepuasaan anda.\n" +
                    "\n" +
                    "Di bandrol dengan harga murah untuk kalangan menengah kebawah dan tidak membuat kantong anda kering tentunya.\n" +
                    "\n" +
                    "SPESIFIKASI :\n" +
                    "Bahan : Fleece PE GRAMASI 240\n" +
                    "\n" +
                    "++ Size / Ukuran : Ukuran Lokal\n" +
                    "(Lebar x Panjang x Panjang Lengan)\n" +
                    "M = 52cm x 66cm x 60cm ( Pria/Wanita Tinggi Maksimal 163cm )\n" +
                    "L = 56cm x 68cm x 62cm ( Pria/Wanita Tinggi Maksimal 172cm )\n" +
                    "XL = 60cm x 71cm x 64cm ( Pria/WanitaTinggi Maksimal 185cm )\n" +
                    "\n" +
                    "NB :\n" +
                    "* CANTUMKAN UKURAN DAN WARNA DI KETERANGAN ORDER\n" +
                    "* SERTA CANTUMKAN 2 PILIHAN WARNA ALTERNATIF, JIKA WARNA UTAMA KOSONG KAMI KIRIM WARNA ALTERNATIF PILIHAN ANDA UNTUK MEMPERCEPAT PROSES PESANAN ANDA"
        ),
        Item(
            "Kaos Polos Hitam",
            20000,
            520,
            R.drawable.gambar_3,
            "Deskripsi Kaos Polos Cotton Combed 20s Hitam - S\n" +
                    "\n" +
                    "Kaos Polos cotton combed 20s standar distro, bahan cotton combed 20s standar distro yang halus dan lembut. Tanpa merek, cocok untuk sablon DTG, digital, atau manual, ready stock dan siap kirim gojek wilayah jakarta. Tersedia ukuran S sampai XXXL.\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "Ukuran (lebar x panjang) cm\n" +
                    "\n" +
                    "S = 38 x 58\n" +
                    "\n" +
                    "M = 41 x 63\n" +
                    "\n" +
                    "ML (Medium Large) / All Size = 44 x 65\n" +
                    "\n" +
                    "L = 50 x 69\n" +
                    "\n" +
                    "XL = 52 x 71\n" +
                    "\n" +
                    "XXL = 56 x 76\n" +
                    "\n" +
                    "XXXL = 67 x 77"
        ),
        Item(
            "Baju Polo Hitam",
            37000,
            550,
            R.drawable.gambar_4,
            "Deskripsi KAOS POLO HITAM - KAOS POLOS - KAOS HITAM - KAOS KRAH - KAOS PRIA - Hitam, L\n" +
                    "HARAP ORDER SESUAI VARIAN UKURAN, BEDA UKURAN BEDA HARGA.\n" +
                    "ORDER AKAN DI PROSES SESUAI UKURAN YANG DI KLIK BELI.\n" +
                    "\n" +
                    "BEST SELLER!!!\n" +
                    "KAOS POLO HITAM POLOS\n" +
                    "\n" +
                    "BHN KAOS LAKOS\n" +
                    "2 KANCING\n" +
                    "\n" +
                    "\n" +
                    "KETERANGAN UKURAN :\n" +
                    "UK M (LD45CM PJG62cm)\n" +
                    "UK L (LD 48CM PJG 68CM)\n" +
                    "UK XL (LD52CM PJG 71CM)\n" +
                    "TOLERANSI BEDA 1-2CM\n" +
                    "LD adalah lebar dada\n" +
                    "P adalah panjang kaos\n" +
                    "\n" +
                    "SEBELUM ORDER SILAHKAN DIUKUR DULU BAJU ANDA.\n" +
                    "\n" +
                    "READY STOCK\n" +
                    "\n" +
                    "BISA UNTUK COWOK / CEWEK\n" +
                    "COCOK UNTUK KAOS SANTAI, CASUAL, BISA UNTUK KAOS SERAGAM DAN SEGALA KEBUTUHAN"
        ),
        Item(
            "Kaos Hitam C",
            30000,
            500,
            R.drawable.gambar_5,
            "Deskripsi Houseofcuff Kaos Polos Pria Premium Katun Combed Lengan Pendek Hitam - Hitam, XXXXL\n" +
                    "Deskripsi Produk :\n" +
                    "\n" +
                    "- Bahan Kain 100% Premium Cotton-Combed 30s Ultrasoft. Bahan sejuk dan nyaman dipakai, gak gampang kusut, gak gampang berbulu dan mudah dicuci\n" +
                    "\n" +
                    "- Benang Jahitan 100% Cotton\n" +
                    "\n" +
                    "- Gramasi kain 140-160 gr/m2; Tidak setebal 24s dan tidak setipis 40s. Gramasi terlaris saat ini.\n" +
                    "\n" +
                    "- Jahitan leher: Double Stick; lebih kuat, gak gampang kendor.\n" +
                    "\n" +
                    "- Jahitan pundak: Rantai; standar jahitan distro mall\n" +
                    "\n" +
                    "- Jahitan Tangan dan Bawah: Overdeck, Jahitan paling unik dan kuat. Salah satu ciri keunikan dari produk kami bisa dilihat dari bagian ini.\n" +
                    "\n" +
                    "- Model Unisex, bisa dipakai untuk Pria dan Wanita\n" +
                    "\n" +
                    "- Ukuran tersedia (S, M, L, XL, XXL, XXXL, XXXXL)\n" +
                    "\n" +
                    "- Setiap pembelian produk houseofcuff mendapatkan kemasan bubble wrap dengan plastik / kotak packaging\n" +
                    "\n" +
                    "Note :\n" +
                    "\n" +
                    "- pada dasarnya foto produk houseofcuff adalah asli produk kami, sehingga warna foto dengan barang asli akan sesuai. toleransi perbedaan warna karena faktor cahaya, kamera dan layar monitor / hape anda 3% - 7%.\n" +
                    "\n" +
                    "- houseofcuff memberikan garansi tukar produk atau refund uang kembali full selama 7 hari sejak barang diterima, apabila barang yang diterima tidak cocok, atau apapun alasannya.\n" +
                    "\n" +
                    "- dilarang menggunakan foto-foto houseofcuff tanpa ijin!"
        )
    )
}