# Keep all Route classes explicitly, assuming Route is an object or class
-keep class id.buaja.sainggik.feature.**.navigation.*Route { *; }

# Keep their class names (for ::class.simpleName)
-keepnames class id.buaja.sainggik.feature.**.navigation.*Route