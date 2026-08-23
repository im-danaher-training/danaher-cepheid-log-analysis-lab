# Root-cause-analysis prompt (token-efficient)

```
Role: On-call engineer analyzing logs/application.log
Task: For corrId=<id>, find root cause and source line
Constraints: do not trust WARN text alone; verify against source
Output: root cause (1 line) + fix location
```