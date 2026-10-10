# 🎨 Paint.NET Integration for IntelliJ IDEA

A lightweight IntelliJ IDEA plugin that seamlessly integrates **[Paint.NET](https://www.getpaint.net/)** into your IDE workflow. Open, edit, and create images directly from the Project View or Editor with automatic detection and multi-file support.

---

## ✨ Features

- 🖼️ **Edit in Paint.NET**: Right-click any image (`.png`, `.jpg`, `.jpeg`, `.bmp`, `.gif`, `.webp`, `.tga`) in the Project View or Editor to open it in Paint.NET.
- 🎨 **Create New Image**: Right-click any folder or use the **New...** menu to create a new blank image file in your project and instantly edit it in Paint.NET.
- 📂 **Multi-File Selection**: Select multiple images in the Project View and open all of them in Paint.NET at once.
- ⚡ **Auto-Detection**: Automatically detects your Paint.NET installation from common directories, Microsoft Store paths, or the Windows Registry.
- ⚙️ **Configurable Executable Path**: Custom install location? Easily configure the path to `PaintDotNet.exe` under **Settings → Tools → Paint.NET Integration**.
- ⌨️ **Keyboard Shortcut**: Press <kbd>Ctrl</kbd> + <kbd>Shift</kbd> + <kbd>P</kbd> on any selected image file for instant editing.
- 💾 **Auto-Save Protection**: Unsaved changes in IntelliJ are automatically saved before launching Paint.NET.

---

## 📥 Installation

### Method 1: Install from Pre-built ZIP (Recommended)

1. Download the latest `PaintDotNetIntegration-x.x.zip` from the **[Releases](../../releases)** tab.
2. In IntelliJ IDEA, go to **File → Settings** (or **Preferences** on macOS).
3. Select **Plugins**, click the ⚙️ gear icon at the top right, and choose **Install Plugin from Disk...**.
4. Select the downloaded `.zip` file and restart IntelliJ.

### Method 2: Build from Source

```bash
# Clone the repository
git clone https://github.com/your-username/PaintDotNetIntegration.git
cd PaintDotNetIntegration

# Build the plugin ZIP
./gradlew buildPlugin
```

The compiled plugin ZIP will be located at:
`build/distributions/PaintDotNetIntegration-1.0-SNAPSHOT.zip`

---

## 🚀 How to Use

| Action | Where to find | Shortcut |
| :--- | :--- | :--- |
| **Edit in Paint.NET** | Right-click image file in **Project View** or **Editor** | <kbd>Ctrl</kbd> + <kbd>Shift</kbd> + <kbd>P</kbd> |
| **Create Image in Paint.NET** | Right-click folder → **New** → **Create Image in Paint.NET** | — |
| **Settings & Path Config** | **Settings** → **Tools** → **Paint.NET Integration** | — |

---

## ⚙️ Configuration

If Paint.NET is not installed in the standard `C:\Program Files\paint.net\` location:

1. Open **Settings → Tools → Paint.NET Integration**.
2. Click **Auto-detect** to let the plugin search your system automatically.
3. Or manually browse to select your `PaintDotNet.exe` path.
4. Click **Apply**.

---

## 📋 Requirements

- **IDE**: IntelliJ IDEA 2024.1+ (Community or Ultimate) or other JetBrains IDEs.
- **OS**: Windows 10 / 11.
- **Software**: [Paint.NET](https://www.getpaint.net/) installed on your machine.

---

## 📄 License

Distributed under the [MIT License](LICENSE).
