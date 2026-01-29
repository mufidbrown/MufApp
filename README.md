# 🚀 Backend - Enterprise-Grade System

## 📌 Overview
This project is an **enterprise-grade backend system** designed with a strong focus on **security, scalability, maintainability, and robustness**.  
It reflects hands-on experience in building backend services that go beyond basic CRUD by implementing **authentication, business workflow, caching, asynchronous processing, and clean architecture principles**.

---

## 🔐 Authentication & Authorization (JWT)
- Implemented **JWT Access Token & Refresh Token**
- Short-lived access token to minimize security risks
- Refresh token is strictly used to re-issue access tokens (not for accessing business APIs)
- Role-based authorization (RBAC)
- Token management and invalidation using **Redis**

**Goal:** Ensure secure authentication while keeping the system scalable and stateless.

---

## 🧩 System Design & Architecture
- Designed using **SOLID principles**
- Clear separation of concerns:
  - Controller
  - Service (Interface & Implementation)
  - Repository
  - DTO (Request & Response)
- Modular and extensible architecture
- Easy to maintain and scale as business complexity grows

---

## 👥 User & Role Management
- User, Role, and Permission management
- Supports many-to-many relationship between users and roles
- Designed to be extensible for enterprise use cases
- Audit-ready and soft-delete friendly

---

## 🔄 Business Workflow
- Implemented **state-based business workflow**
- Example flow:



# 📊 CRM System Overview

CRM (Customer Relationship Management) system ini dirancang untuk mengelola data pelanggan, workflow operasional, serta reporting secara terstruktur, konsisten, dan scalable.

Sistem dibagi ke dalam beberapa aspek utama sebagai berikut.

---

## A. Data Management (Master Data)

**Users**  
Semua user (sales, CS, admin) tercatat dan bisa di-assign ke lead, task, atau activity.

**Lead Sources**  
Data sumber lead tercatat → bisa analisis channel mana paling efektif.

**Leads**  
Semua lead tercatat lengkap, unik (phone/email), punya owner & status.

**Lead History**  
Semua perubahan status lead tercatat → audit trail.

**Accounts & Contacts**  
Lead yang berhasil dikonversi menjadi account & contact → data perusahaan/pelanggan tersentralisasi.

**Conversions**  
Menyimpan informasi lead yang sudah jadi pelanggan → bisa track conversion rate.

✅ **Hasil:**  
Semua data pelanggan terstruktur, terhubung, dan audit-able. Tidak ada data hilang atau tercecer.

---

## B. Operations / Workflow

**Activities & Notes**  
Semua interaksi (call, email, meeting) dan catatan tersimpan → user bisa cek riwayat interaksi.

**Tasks & Reminders**  
Semua tugas sales/CS otomatis tercatat → workflow jelas, reminder mengingatkan follow-up.

**Opportunities & StageHistory**  
Deal/penjualan tercatat, stage history menyimpan pergerakan → pipeline tracking.

**Assignments**  
Polymorphic assignments → user bisa diassign ke lead, opportunity, account, dsb.

**WorkflowConfig**  
Menentukan alur stage dari lead → opportunity → conversion → deal.

✅ **Hasil:**

- Sales bisa follow-up lead sesuai prioritas.
- Semua aktivitas bisa dilacak → tidak ada follow-up yang terlewat.
- Pipeline penjualan terstruktur dan mudah dianalisis.

---

## C. Reporting / Insight

**SalesPerformanceReport**  
Laporan performa user → total deal, revenue, win rate.

**PipelineSummary**  
Laporan ringkasan pipeline → deal count, total value per stage.

**ExportJob**  
Bisa export data untuk reporting eksternal.

✅ **Hasil:**

- Manajemen bisa membuat keputusan berbasis data → alokasi lead, penyesuaian target sales, optimasi workflow.
- KPI sales, performa tim, pipeline, dan revenue terlihat jelas.

---

## D. Standardisasi / Konsistensi

**Enums**  
LeadStatus, OpportunityStage, ActivityType, TaskStatus → memastikan data selalu konsisten dan valid.

**Polymorphic relations**  
Notes, Reminders, Assignments, Activities → fleksibel untuk berbagai entity tanpa membuat tabel baru untuk tiap entity.

✅ **Hasil:**

- Sistem terstandarisasi → meminimalisir kesalahan input.
- Skalabilitas tinggi → bisa menambahkan entity baru atau modul baru tanpa mengubah core.


