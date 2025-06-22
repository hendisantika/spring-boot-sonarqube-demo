#!/bin/bash

# SonarQube configuration
SONAR_HOST="http://localhost:9000"  # Change this to your SonarQube server URL
SONAR_TOKEN="sqp_b2397f01952a6c192123496891f4850f81a3137f"      # Change this to your SonarQube token
PROJECT_KEY="SonarQube-Demo"  # Change this to match your project

# Threshold configuration
MAX_BUGS=0
MAX_CODE_SMELLS=10
MAX_VULNERABILITIES=0
MAX_SECURITY_HOTSPOTS=0

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
NC='\033[0m' # No Color

# Function to get metric value from SonarQube
get_metric() {
    local metric=$1
    value=$(curl -s -u "${SONAR_TOKEN}:" "${SONAR_HOST}/api/measures/component?component=${PROJECT_KEY}&metricKeys=${metric}" | \
        grep -o '"value":"[^"]*"' | cut -d'"' -f4)
    if [ -z "$value" ]; then
        echo "0"
    else
        echo "$value"
    fi
}

echo "🔍 Checking SonarQube metrics..."

# Get current metrics
BUGS=$(get_metric "bugs")
CODE_SMELLS=$(get_metric "code_smells")
VULNERABILITIES=$(get_metric "vulnerabilities")
SECURITY_HOTSPOTS=$(get_metric "security_hotspots")

# Print current metrics
echo -e "\nCurrent metrics:"
echo "Bugs: ${BUGS}"
echo "Code Smells: ${CODE_SMELLS}"
echo "Vulnerabilities: ${VULNERABILITIES}"
echo "Security Hotspots: ${SECURITY_HOTSPOTS}"

# Check thresholds
FAILED=0

if [ "${BUGS}" -gt "${MAX_BUGS}" ]; then
    echo -e "${RED}❌ Too many bugs: ${BUGS} (maximum: ${MAX_BUGS})${NC}"
    FAILED=1
fi

if [ "${CODE_SMELLS}" -gt "${MAX_CODE_SMELLS}" ]; then
    echo -e "${RED}❌ Too many code smells: ${CODE_SMELLS} (maximum: ${MAX_CODE_SMELLS})${NC}"
    FAILED=1
fi

if [ "${VULNERABILITIES}" -gt "${MAX_VULNERABILITIES}" ]; then
    echo -e "${RED}❌ Too many vulnerabilities: ${VULNERABILITIES} (maximum: ${MAX_VULNERABILITIES})${NC}"
    FAILED=1
fi

if [ "${SECURITY_HOTSPOTS}" -gt "${MAX_SECURITY_HOTSPOTS}" ]; then
    echo -e "${RED}❌ Too many security hotspots: ${SECURITY_HOTSPOTS} (maximum: ${MAX_SECURITY_HOTSPOTS})${NC}"
    FAILED=1
fi

if [ "${FAILED}" -eq 1 ]; then
    echo -e "\n${RED}✖ Commit rejected: Quality gates not met${NC}"
    exit 1
else
    echo -e "\n${GREEN}✔ All quality gates passed${NC}"
    exit 0
fi