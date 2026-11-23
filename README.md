# 📦 Sistema de Gestión y Rastreo — Paquetería DJP

Aplicación web desarrollada en Spring Boot para el control de envíos, rastreo de paquetes por código de seguimiento, gestión de historial de estados, unidades de reparto y administración de personal, organizada mediante ramas de trabajo integradas en `main`.

---

## 👥 Integrantes y Ramas de Trabajo

El desarrollo colaborativo se organizó mediante ramas independientes integradas finalmente en `main`:

| Integrante | Usuario GitHub | Rama de Desarrollo | Módulo Principal |
| :--- | :--- | :--- | :--- |
| **Daniela** | [@daniela45-ux](https://github.com/daniela45-ux) | `feature/modelo-y-persistencia-h2` | Entidades del dominio (`Paquete`, `Unidad`, `Usuario`, `HistorialEstado`), repositorios JPA y persistencia H2 |
| **Diego** | [@Diegoo0305](https://github.com/Diegoo0305) | `feature/logica-y-servicios` | Reglas de negocio, servicios (`PaqueteService`, `UnidadService`, `UsuarioService`) y carga inicial (`DataInitializer`) |
| **Secundino** | [@Secundinojollette-Arch](https://github.com/Secundinojollette-Arch) | `feature/controladores-y-flujo` | Controladores web (`HomeController`, `PaqueteController`, `UnidadController`) y flujo de peticiones |
| **Dann** | [@dannbruno16D](https://github.com/dannbruno16D) | `feature/interfaz-thymeleaf-djp` | Vistas Thymeleaf (`index.html`, `paquetes.html`, `unidades.html`), estilos (`djp.css`) y validación de interfaz |

---

## 🚀 Funcionalidades Principales

* **Rastreo de Paquetes (`/`)**: Consulta de envíos mediante código de rastreo con visualización de los datos del paquete y su historial de estados (`HISTORIAL_ESTADOS`).
* **Gestión de Paquetes (`/paquetes`)**: Registro de paquetes con datos de envío (código postal, teléfono de contacto, remitente, destinatario, descripción de contenido y peso) y actualización de estados con ubicación.
* **Unidades y Personal (`/unidades`)**: Alta y eliminación de unidades de reparto y consulta/registro de personal (`USUARIOS`) con sus roles asignados.
* **Historial Trazable**: Registro cronológico de eventos, ubicaciones y cambios de estado asociados a cada paquete (`HISTORIAL_ESTADOS`).
* **Consola de Base de Datos y Exportación SQL**: Acceso a H2 Console para consultar tablas y exportar el script SQL (`djp/export_djp.sql`).

---

## 📁 Documentación, Base de Datos y Archivos Clave

* **Punto de Entrada Spring Boot**: `djp/src/main/java/com/paqueteria/djp/DjpApplication.java`
* **Vistas Thymeleaf**: `djp/src/main/resources/templates/` (`index.html`, `paquetes.html`, `unidades.html`)
* **Estilos Estáticos**: `djp/src/main/resources/static/djp.css`
* **Script SQL Exportado**: `djp/export_djp.sql` (estructura y registros exportados desde H2)

---

## 🛠️ Stack Tecnológico

* **Backend**: Java + Spring Boot (Spring Web MVC, Spring Data JPA)
* **Frontend**: Thymeleaf + HTML5 + CSS3 (`djp.css`)
* **Base de Datos**: H2 Database (`jdbc:h2:mem:paqueteriadjpdb`)
* **Arquitectura**: Patrón por capas (`model`, `repository`, `service`, `controller`)

---

## ▶️ Cómo Ejecutar el Proyecto

1. **Clonar el repositorio:**

```bash
git clone [https://github.com/dannbruno16D/PaqueteriaJ.git](https://github.com/dannbruno16D/PaqueteriaJ.git)
cd PaqueteriaJ/djp