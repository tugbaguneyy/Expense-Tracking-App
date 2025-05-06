package com.example.trackingapp.ui.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navController: NavController
) {
    var darkMode by remember { mutableStateOf(false) }
    var selectedCurrency by remember { mutableStateOf("TRY") }
    var showLogoutDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
    ) {
        // Account Settings Section
        Text(
            text = "Hesap",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        // Change Password
        SettingsItem(
            icon = Icons.Outlined.Lock,
            title = "Şifre Değiştir",
            subtitle = "Hesap şifrenizi güncelleyin",
            trailingContent = {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            },
            onClick = { /* TODO */ }
        )

        // Edit Profile
        SettingsItem(
            icon = Icons.Outlined.Person,
            title = "Profili Düzenle",
            subtitle = "Kişisel bilgilerinizi güncelleyin",
            trailingContent = {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            },
            onClick = { /* TODO */ }
        )

        // Logout
        SettingsItem(
            icon = Icons.Outlined.Logout,
            title = "Oturumu Kapat",
            subtitle = "Hesabınızdan çıkış yapın",
            trailingContent = {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            },
            onClick = { showLogoutDialog = true }
        )

        // App Settings Section
        Text(
            text = "Uygulama",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        // Theme Settings
        SettingsItem(
            icon = Icons.Outlined.DarkMode,
            title = "Karanlık Mod",
            subtitle = "Uygulamayı karanlık temada kullan",
            trailingContent = {
                Switch(
                    checked = darkMode,
                    onCheckedChange = { darkMode = it },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = MaterialTheme.colorScheme.primary,
                        checkedTrackColor = MaterialTheme.colorScheme.primaryContainer
                    )
                )
            }
        )

        // Currency Settings
        SettingsItem(
            icon = Icons.Outlined.CurrencyExchange,
            title = "Para Birimi",
            subtitle = selectedCurrency,
            trailingContent = {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            },
            onClick = { /* TODO */ }
        )

        // Notifications Section
        Text(
            text = "Bildirimler",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        SettingsItem(
            icon = Icons.Outlined.Notifications,
            title = "Bildirim Ayarları",
            subtitle = "Bildirim tercihlerinizi yönetin",
            trailingContent = {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            },
            onClick = { /* TODO */ }
        )

        // Data Management Section
        Text(
            text = "Veri Yönetimi",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        // Export Data
        SettingsItem(
            icon = Icons.Outlined.Download,
            title = "Verileri Dışa Aktar",
            subtitle = "Harcama verilerinizi yedekleyin",
            trailingContent = {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            },
            onClick = { /* TODO */ }
        )

        // Import Data
        SettingsItem(
            icon = Icons.Outlined.Upload,
            title = "Verileri İçe Aktar",
            subtitle = "Yedeklenen verilerinizi geri yükleyin",
            trailingContent = {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            },
            onClick = { /* TODO */ }
        )

        // Clear Data
        SettingsItem(
            icon = Icons.Outlined.Delete,
            title = "Verileri Temizle",
            subtitle = "Tüm harcama verilerinizi silin",
            trailingContent = {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            },
            onClick = { /* TODO */ }
        )

        // About Section
        Text(
            text = "Hakkında",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        // App Version
        SettingsItem(
            icon = Icons.Outlined.Android,
            title = "Uygulama Versiyonu",
            subtitle = "1.0.0",
            trailingContent = {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            },
            onClick = { /* TODO */ }
        )

        // Privacy Policy
        SettingsItem(
            icon = Icons.Outlined.PrivacyTip,
            title = "Gizlilik Politikası",
            subtitle = "Gizlilik politikamızı görüntüleyin",
            trailingContent = {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            },
            onClick = { /* TODO */ }
        )

        // Terms of Use
        SettingsItem(
            icon = Icons.Outlined.Description,
            title = "Kullanım Koşulları",
            subtitle = "Kullanım koşullarını görüntüleyin",
            trailingContent = {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            },
            onClick = { /* TODO */ }
        )

        Spacer(modifier = Modifier.height(24.dp))
    }

    if (showLogoutDialog) {
        AlertDialog(
            onDismissRequest = { showLogoutDialog = false },
            title = { Text("Oturumu Kapat") },
            text = { Text("Oturumunuzu kapatmak istediğinizden emin misiniz?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        navController.navigate("login") {
                            popUpTo("home") { inclusive = true }
                        }
                    }
                ) {
                    Text("Evet")
                }
            },
            dismissButton = {
                TextButton(onClick = { showLogoutDialog = false }) {
                    Text("İptal")
                }
            }
        )
    }
}

@Composable
private fun SettingsItem(
    icon: ImageVector,
    title: String,
    subtitle: String,
    trailingContent: @Composable (() -> Unit)? = null,
    onClick: (() -> Unit)? = null
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = 2.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .then(
                    if (onClick != null) {
                        Modifier.clickable(onClick = onClick)
                    } else {
                        Modifier
                    }
                )
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            }

            if (trailingContent != null) {
                Spacer(modifier = Modifier.width(8.dp))
                trailingContent()
            }
        }
    }
}