# Log Analysis & Production Debugging Lab

## Objective
Learn to use GitHub Copilot inside **IntelliJ IDEA** to complete a realistic Danaher/Cepheid engineering task.

## Duration
40-45 minutes

## Prerequisites
- IntelliJ IDEA (Community or Ultimate)
- JDK 17+
- Maven 3.9+
- GitHub Copilot and GitHub Copilot Chat plugins enabled in IntelliJ
- Git

## IntelliJ Setup
1. Open IntelliJ IDEA and choose **File > Open**, then select this repository folder.
2. Trust the project when prompted and let IntelliJ auto-import the Maven project.
3. Confirm the Project SDK is Java 17 in **File > Project Structure > Project**.
4. Open the **Maven** tool window (right sidebar) to run 	est/erify goals.
5. Open **GitHub Copilot Chat** from the right tool bar, keep the relevant file active so Copilot has context.

## Scenario
CepheidDx operations shared `logs/application.log` covering three separate incidents from a single morning: a null pointer failure, an inventory timeout, and a suspiciously low tax calculation.

## Starting Point
The log file contains successful transactions mixed with three real issues. A WARN about "Unexpected low tax amount" is a misleading symptom pointing at the tax service, but the real defect is a wrong lookup key in `TaxCalculator`. Related source lives in `OrderLookupService`, `InventoryClient`, and `TaxCalculator`.

## Hands-on Tasks
1. Read through `logs/application.log` and list every correlation ID that has a WARN or ERROR entry.
2. For correlation id `c110af22`, trace the stack trace to `OrderLookupService.java:15` and ask Copilot to explain the root cause.
3. For correlation id `90ee7710`, determine whether the retry masked or resolved the underlying inventory timeout.
4. For correlation id `3bd0459e`, do not trust the WARN message alone - trace `calculateTax` for the US-WEST region and find the actual defect.
5. Fix the `OrderLookupService` NPE and the `TaxCalculator` US-WEST rate defect.
6. Add regression tests for both fixes and re-run the suite to confirm the issues no longer occur.

## Validation
Run from the IntelliJ **Terminal** tab (Alt+F12) or the Maven tool window:

```bash
mvn test
```

## Expected Result
Both defects (NPE in OrderLookupService, wrong US-WEST tax rate) are fixed and covered by regression tests, with a clear root-cause explanation for each.

## Troubleshooting
- If you cannot find the tax defect, compare the `US-EAST` and `US-WEST` branches of the switch statement line by line.
- Remember: the WARN message in the log is a symptom, not the root cause - do not "fix" the warning message itself.

## Optional Challenge
Add a fourth log scenario of your own (e.g., a duplicate order submission) and write the source-level fix plus a regression test for it.

## Copilot Customization Guide

**Already provided:** `.github/instructions/copilot-instructions.md`.

**New prompt file:** `.github/prompts/rca-prompt.md`

```
Role: On-call engineer analyzing logs/application.log
Task: For corrId=<id>, find root cause and source line
Constraints: do not trust WARN text alone; verify against source
Output: root cause (1 line) + fix location
```

**Optional stretch tasks (build these yourself - not provided):**
- Custom agent: create `.github/agents/incident-rca-agent.agent.md` defining a persona that traces a correlation ID from logs to the exact source line and root cause.
- Skill: create `.github/skills/log-triage-checklist/SKILL.md` capturing a reusable checklist for separating misleading symptoms from true root cause in production logs.
- `AGENTS.md`: optionally add a root-level file summarizing repo conventions for cross-tool agent compatibility (Copilot CLI and other agentic tools read this file).

**Enterprise tip:** Enterprise Copilot usage favors small, structured, reusable prompts over long free-form ones. State `Role / Task / Constraints / Output` in under ~5 lines - this keeps token usage low and responses focused, which matters when Copilot is used constantly across a team.
