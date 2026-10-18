package com.mike.portfolio

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Background = Color(0xFF070B14)
private val CardColor = Color(0xFF121C2E)
private val Cyan = Color(0xFF00E5FF)
private val Blue = Color(0xFF168CFF)
private val Muted = Color(0xFF9BAAC2)

class MainActivity : ComponentActivity() {

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    window.statusBarColor =
        android.graphics.Color.rgb(7, 11, 20)

    window.navigationBarColor =
        android.graphics.Color.rgb(7, 11, 20)

    setContent {
        MaterialTheme(
            colorScheme = darkColorScheme(
                primary = Cyan,
                secondary = Blue,
                background = Background,
                surface = CardColor
            )
        ) {
            PortfolioApp(
                onOpenTelegram = {
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://t.me/TechElons")
                    )
                    startActivity(intent)
                }
            )
        }
    }
}

}

@Composable
fun PortfolioApp(
onOpenTelegram: () -> Unit
) {
var selectedTab by remember {
mutableStateOf("Home")
}

var adminUnlocked by remember {
    mutableStateOf(false)
}

var pin by remember {
    mutableStateOf("")
}

Scaffold(
    containerColor = Background,

    bottomBar = {
        NavigationBar(
            containerColor = CardColor
        ) {
            val tabs = listOf(
                "Home",
                "About",
                "Projects",
                "Community",
                "Admin"
            )

            val icons = listOf(
                Icons.Default.Home,
                Icons.Default.Person,
                Icons.Default.Code,
                Icons.Default.Groups,
                Icons.Default.AdminPanelSettings
            )

            tabs.forEachIndexed { index, tab ->
                NavigationBarItem(
                    selected = selectedTab == tab,
                    onClick = {
                        selectedTab = tab
                    },
                    icon = {
                        Icon(
                            imageVector = icons[index],
                            contentDescription = tab
                        )
                    },
                    label = {
                        Text(tab, fontSize = 9.sp)
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Cyan,
                        selectedTextColor = Cyan,
                        indicatorColor = Blue.copy(alpha = 0.18f),
                        unselectedIconColor = Muted,
                        unselectedTextColor = Muted
                    )
                )
            }
        }
    }
) { padding ->

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .background(Background)
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {
        when (selectedTab) {
            "Home" -> HomeScreen {
                selectedTab = "Projects"
            }

            "About" -> AboutScreen()

            "Projects" -> ProjectsScreen()

            "Community" -> CommunityScreen(
                onOpenTelegram = onOpenTelegram
            )

            "Admin" -> AdminScreen(
                unlocked = adminUnlocked,
                pin = pin,
                onPinChange = {
                    pin = it
                },
                onUnlock = {
                    if (pin == "2580") {
                        adminUnlocked = true
                        pin = ""
                    }
                },
                onLock = {
                    adminUnlocked = false
                }
            )
        }

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        HorizontalDivider(
            color = CardColor
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        Text(
            text = "MIKE PORTFOLIO • VERSION 1.0.0",
            color = Muted,
            fontSize = 10.sp,
            modifier = Modifier.align(
                Alignment.CenterHorizontally
            )
        )
    }
}

}

@Composable
fun HomeScreen(
onProjects: () -> Unit
) {
Column(
verticalArrangement = Arrangement.spacedBy(20.dp)
) {
Text(
text = "MIKE PORTFOLIO",
color = Cyan,
fontSize = 15.sp,
fontWeight = FontWeight.Bold
)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF10264A),
                        Color(0xFF07333C),
                        Color(0xFF10182B)
                    )
                ),
                RoundedCornerShape(28.dp)
            )
            .padding(24.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Surface(
                color = Cyan.copy(alpha = 0.12f),
                shape = RoundedCornerShape(50.dp)
            ) {
                Text(
                    text = "● AVAILABLE TO LEARN & BUILD",
                    color = Cyan,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(
                        horizontal = 12.dp,
                        vertical = 8.dp
                    )
                )
            }

            Text(
                text = "HELLO,\nWORLD.",
                color = Color.White,
                fontSize = 42.sp,
                fontWeight = FontWeight.ExtraBold,
                lineHeight = 46.sp
            )

            Text(
                text = "I'm Mikiyaas Worku.",
                color = Cyan,
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Grade 7 student • Programmer • Technology enthusiast",
                color = Color(0xFFD5E4F7),
                fontSize = 14.sp,
                lineHeight = 23.sp
            )

            Button(
                onClick = onProjects,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Cyan,
                    contentColor = Background
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(
                    Icons.Default.Code,
                    contentDescription = null
                )

                Spacer(Modifier.width(8.dp))

                Text("EXPLORE MY PROJECTS")
            }
        }
    }

    Text(
        text = "MY DEVELOPER PROFILE",
        color = Color.White,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold
    )

    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        StatCard(
            title = "FOCUS",
            value = "Coding",
            modifier = Modifier.weight(1f)
        )

        StatCard(
            title = "PASSION",
            value = "Technology",
            modifier = Modifier.weight(1f)
        )
    }

    GlassCard {
        Icon(
            Icons.Default.Terminal,
            contentDescription = null,
            tint = Cyan,
            modifier = Modifier.size(32.dp)
        )

        Text(
            text = "BUILD. LEARN. INNOVATE.",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )

        Text(
            text = "Exploring programming, Android development, " +
                "automation, bots and software engineering.",
            color = Muted,
            fontSize = 13.sp,
            lineHeight = 21.sp
        )
    }
}

}

@Composable
fun AboutScreen() {
Column(
verticalArrangement = Arrangement.spacedBy(18.dp)
) {
ScreenTitle(
"ABOUT ME",
"The person behind the code."
)

    GlassCard {
        Icon(
            Icons.Default.Person,
            contentDescription = null,
            tint = Cyan,
            modifier = Modifier.size(42.dp)
        )

        Text(
            text = "Mikiyaas Worku",
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Student • Programmer",
            color = Cyan,
            fontSize = 14.sp
        )

        Text(
            text = "I'm a Grade 7 student who loves programming " +
                "and building software projects. I'm interested " +
                "in Android apps, Telegram bots, web development, " +
                "and learning how computers work.",
            color = Muted,
            fontSize = 14.sp,
            lineHeight = 23.sp
        )
    }

    GlassCard {
        Text(
            text = "TECHNOLOGY INTERESTS",
            color = Color.White,
            fontWeight = FontWeight.Bold
        )

        SkillRow("Python", "Bots and automation")
        SkillRow("Kotlin", "Android applications")
        SkillRow("HTML / CSS / JavaScript", "Web development")
        SkillRow("Assembly", "Low-level programming")
        SkillRow("GitHub Actions", "Automated builds")
    }

    Text(
        text = "My goal is to keep learning, create useful " +
            "projects, and become a skilled software engineer.",
        color = Muted,
        fontSize = 14.sp,
        lineHeight = 23.sp
    )
}

}

@Composable
fun ProjectsScreen() {
var search by remember {
mutableStateOf("")
}

val projects = listOf(
    Triple(
        "MIKE PORTFOLIO",
        "Android",
        "A personal developer portfolio application."
    ),
    Triple(
        "Telegram Bots",
        "Python",
        "Community tools, automation and bot development."
    ),
    Triple(
        "FORCE Community",
        "Telegram",
        "A community project focused on engagement."
    ),
    Triple(
        "MIKE GRAM",
        "Android",
        "A messaging-app customization project."
    ),
    Triple(
        "Web Experiments",
        "Web",
        "Experiments with HTML, CSS and JavaScript."
    )
)

Column(
    verticalArrangement = Arrangement.spacedBy(16.dp)
) {
    ScreenTitle(
        "MY PROJECTS",
        "Ideas turned into code."
    )

    OutlinedTextField(
        value = search,
        onValueChange = {
            search = it
        },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        label = {
            Text("Search projects")
        },
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = null
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Cyan,
            focusedLabelColor = Cyan,
            cursorColor = Cyan
        )
    )

    val filteredProjects = projects.filter {
        it.first.contains(search, ignoreCase = true) ||
            it.second.contains(search, ignoreCase = true) ||
            it.third.contains(search, ignoreCase = true)
    }

    if (filteredProjects.isEmpty()) {
        Text(
            text = "No matching projects found.",
            color = Muted
        )
    }

    filteredProjects.forEach { project ->
        GlassCard {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Default.Code,
                    contentDescription = null,
                    tint = Cyan,
                    modifier = Modifier.size(36.dp)
                )

                Spacer(Modifier.width(12.dp))

                Column(
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Text(
                        text = project.first,
                        color = Color.White,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = project.second.uppercase(),
                        color = Cyan,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = project.third,
                        color = Muted,
                        fontSize = 13.sp,
                        lineHeight = 20.sp
                    )
                }
            }
        }
    }

    Text(
        text = "These are starter portfolio entries. Replace " +
            "them with your actual projects as you develop them.",
        color = Muted,
        fontSize = 12.sp,
        lineHeight = 19.sp
    )
}

}

@Composable
fun CommunityScreen(
onOpenTelegram: () -> Unit
) {
Column(
verticalArrangement = Arrangement.spacedBy(18.dp)
) {
ScreenTitle(
"MY COMMUNITY",
"Connect and explore."
)

    GlassCard {
        Icon(
            Icons.Default.Send,
            contentDescription = null,
            tint = Cyan,
            modifier = Modifier.size(48.dp)
        )

        Text(
            text = "TECH ELONS",
            color = Color.White,
            fontSize = 23.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Visit my Telegram community for technology " +
                "and programming content.",
            color = Muted,
            fontSize = 14.sp,
            lineHeight = 22.sp
        )

        Button(
            onClick = onOpenTelegram,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Blue
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Icon(
                Icons.Default.Send,
                contentDescription = null
            )

            Spacer(Modifier.width(8.dp))

            Text("OPEN @TechElons")
        }
    }

    GlassCard {
        Text(
            text = "COMMUNITY LINK",
            color = Cyan,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "https://t.me/TechElons",
            color = Color.White,
            fontSize = 14.sp
        )
    }
}

}

@Composable
fun AdminScreen(
unlocked: Boolean,
pin: String,
onPinChange: (String) -> Unit,
onUnlock: () -> Unit,
onLock: () -> Unit
) {
Column(
verticalArrangement = Arrangement.spacedBy(18.dp)
) {
ScreenTitle(
"ADMIN PANEL",
"Local demonstration dashboard."
)

    if (!unlocked) {
        GlassCard {
            Icon(
                Icons.Default.Lock,
                contentDescription = null,
                tint = Cyan,
                modifier = Modifier.size(44.dp)
            )

            Text(
                text = "ADMIN LOGIN",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Enter the demo PIN to view the local dashboard.",
                color = Muted,
                fontSize = 13.sp
            )

            OutlinedTextField(
                value = pin,
                onValueChange = onPinChange,
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text("Demo PIN")
                },
                singleLine = true,
                visualTransformation =
                    androidx.compose.ui.text.input
                        .PasswordVisualTransformation(),
                keyboardOptions =
                    androidx.compose.foundation.text.KeyboardOptions(
                        keyboardType =
                            androidx.compose.ui.text.input
                                .KeyboardType.NumberPassword
                    ),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Cyan,
                    focusedLabelColor = Cyan,
                    cursorColor = Cyan
                )
            )

            Button(
                onClick = onUnlock,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("UNLOCK DASHBOARD")
            }

            Text(
                text = "Demo PIN: 2580. This is not secure authentication.",
                color = Muted,
                fontSize = 11.sp
            )
        }
    } else {
        GlassCard {
            Icon(
                Icons.Default.AdminPanelSettings,
                contentDescription = null,
                tint = Cyan,
                modifier = Modifier.size(42.dp)
            )

            Text(
                text = "WELCOME, ADMIN",
                color = Color.White,
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "The local demonstration dashboard is unlocked.",
                color = Muted,
                fontSize = 13.sp
            )

            AdminFeature(
                "Profile",
                "Manage your biography"
            )

            AdminFeature(
                "Projects",
                "Plan project management"
            )

            AdminFeature(
                "Appearance",
                "Plan theme customization"
            )

            AdminFeature(
                "Database",
                "Local storage integration is next"
            )

            AdminFeature(
                "Backup",
                "Backup and restore are not implemented yet"
            )

            Button(
                onClick = onLock,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF7A263A)
                )
            ) {
                Icon(
                    Icons.Default.Lock,
                    contentDescription = null
                )

                Spacer(Modifier.width(8.dp))

                Text("LOCK ADMIN")
            }
        }
    }
}

}

@Composable
fun ScreenTitle(
title: String,
subtitle: String
) {
Column(
verticalArrangement = Arrangement.spacedBy(8.dp)
) {
Text(
text = title,
color = Cyan,
fontSize = 25.sp,
fontWeight = FontWeight.ExtraBold
)

    Text(
        text = subtitle,
        color = Muted,
        fontSize = 14.sp
    )
}

}

@Composable
fun GlassCard(
content: @Composable ColumnScope.() -> Unit
) {
Column(
modifier = Modifier
.fillMaxWidth()
.background(
color = CardColor,
shape = RoundedCornerShape(20.dp)
)
.padding(18.dp),
verticalArrangement = Arrangement.spacedBy(12.dp),
content = content
)
}

@Composable
fun StatCard(
title: String,
value: String,
modifier: Modifier = Modifier
) {
Column(
modifier = modifier
.background(
CardColor,
RoundedCornerShape(18.dp)
)
.padding(16.dp),
verticalArrangement = Arrangement.spacedBy(8.dp)
) {
Text(
text = title,
color = Muted,
fontSize = 10.sp,
fontWeight = FontWeight.Bold
)

    Text(
        text = value,
        color = Cyan,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold
    )
}

}

@Composable
fun SkillRow(
skill: String,
description: String
) {
Row(
modifier = Modifier.fillMaxWidth(),
verticalAlignment = Alignment.Top
) {
Icon(
Icons.Default.CheckCircle,
contentDescription = null,
tint = Cyan,
modifier = Modifier.size(18.dp)
)

    Spacer(Modifier.width(10.dp))

    Column(
        verticalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        Text(
            text = skill,
            color = Color.White,
            fontWeight = FontWeight.SemiBold
        )

        Text(
            text = description,
            color = Muted,
            fontSize = 12.sp
        )
    }
}

}

@Composable
fun AdminFeature(
title: String,
description: String
) {
Row(
verticalAlignment = Alignment.Top
) {
Icon(
Icons.Default.CheckCircle,
contentDescription = null,
tint = Cyan,
modifier = Modifier.size(20.dp)
)

    Spacer(Modifier.width(10.dp))

    Column {
        Text(
            text = title,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = description,
            color = Muted,
            fontSize = 12.sp
        )
    }
}

}
