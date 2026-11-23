# 📦 Sistema de Gestión y Rastreo — Paquetería DJP

Aplicación web desarrollada en Spring Boot para el control de envíos, rastreo de paquetes por código de seguimiento, gestión de historial de estados, unidades de reparto y administración de personal, organizada mediante ramas de trabajo integradas en `main`[cite: 1, 2, 12].

---

## 👥 Integrantes y Ramas de Trabajo

El desarrollo colaborativo se organizó mediante ramas independientes integradas finalmente en `main`[cite: 12]:

| Integrante | Usuario GitHub | Rama de Desarrollo | Módulo Principal |
| :--- | :--- | :--- | :--- |
| **Daniela** | [@daniela45-ux](https://github.com/daniela45-ux)[cite: 15] | `feature/modelo-y-persistencia-h2`[cite: 2] | Entidades del dominio (`Paquete`, `Unidad`, `Usuario`, `HistorialEstado`), repositorios JPA y persistencia H2[cite: 1] |
| **Diego** | [@Diegoo0305](https://github.com/Diegoo0305)[cite: 15] | `feature/logica-y-servicios`[cite: 2] | Reglas de negocio, servicios (`PaqueteService`, `UnidadService`, `UsuarioService`) y carga inicial (`DataInitializer`)[cite: 1] |
| **Secundino** | [@Secundinojollette-Arch](https://github.com/Secundinojollette-Arch)[cite: 15] | `feature/controladores-y-flujo`[cite: 2] | Controladores web (`HomeController`, `PaqueteController`, `UnidadController`) y flujo de peticiones[cite: 1] |
| **Dann** | [@dannbruno16D](https://github.com/dannbruno16D)[cite: 1, 15] | `feature/interfaz-thymeleaf-djp`[cite: 2] | Vistas Thymeleaf (`index.html`, `paquetes.html`, `unidades.html`), estilos (`djp.css`) y validación de interfaz[cite: 1] |

---

## 🚀 Funcionalidades Principales

* **Rastreo de Paquetes (`/`)**: Consulta de envíos mediante código de rastreo con visualización de los datos del paquete y su historial de estados (`HISTORIAL_ESTADOS`)[cite: 8].
* **Gestión de Paquetes (`/paquetes`)**: Registro de paquetes con datos de envío (código postal, teléfono de contacto, remitente, destinatario, descripción de contenido y peso) y actualización de estados con ubicación[cite: 9].
* **Unidades y Personal (`/unidades`)**: Alta y eliminación de unidades de reparto y consulta/registro de personal (`USUARIOS`) con sus roles asignados[cite: 1, 8].
* **Historial Trazable**: Registro cronológico de eventos, ubicaciones y cambios de estado asociados a cada paquete (`HISTORIAL_ESTADOS`)[cite: 8, 9].
* **Consola de Base de Datos y Exportación SQL**: Acceso a H2 Console para consultar tablas y exportar el script SQL (`djp/export_djp.sql`)[cite: 8, 9].

---

## 📁 Documentación, Base de Datos y Archivos Clave

* **Punto de Entrada Spring Boot**: `djp/src/main/java/com/paqueteria/djp/DjpApplication.java`[cite: 1, 5]
* **Vistas Thymeleaf**: `djp/src/main/resources/templates/` (`index.html`, `paquetes.html`, `unidades.html`)[cite: 1, 5]
* **Estilos Estáticos**: `djp/src/main/resources/static/djp.css`[cite: 1, 5]
* **Script SQL Exportado**: `djp/export_djp.sql` (estructura y registros exportados desde H2)[cite: 9]

---

## 🛠️ Stack Tecnológico

* **Backend**: Java + Spring Boot (Spring Web MVC, Spring Data JPA)
* **Frontend**: Thymeleaf + HTML5 + CSS3 (`djp.css`)[cite: 1, 5]
* **Base de Datos**: H2 Database (`jdbc:h2:mem:paqueteriadjpdb`)
* **Arquitectura**: Patrón por capas (`model`, `repository`, `service`, `controller`)[cite: 1]

---

## ▶️ Cómo Ejecutar el Proyecto

1. **Clonar el repositorio:**

```bash
git clone [https://github.com/dannbruno16D/PaqueteriaJ.git](https://github.com/dannbruno16D/PaqueteriaJ.git)
cd PaqueteriaJ/djp