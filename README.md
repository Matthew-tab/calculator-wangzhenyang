# Calculator — Maven CLI 计算器

软件开发工具课程 Lab 3 / Lab 4 项目：基于 Maven 的命令行计算器，支持加减乘除，并配套完整的 JUnit 单元测试、JaCoCo 覆盖率报告与 PMD 静态分析。

## 环境

- JDK 17+（开发机为 JDK 25）
- Maven 3.9+
- 依赖：Apache Commons Lang 3.14.0、JUnit Jupiter 5.10.0

## 构建与运行

```bash
mvn clean verify        # 编译、测试、打包、覆盖率报告、PMD 分析
java -cp target/calculator-1.0.jar com.wangzhenyang.App 2 + 2
```

## 单元测试

- `CalculatorTest`：15 个普通用例 + 8 个参数化用例，覆盖四则运算、边界值（0、负数、大数）、除零异常、空白输入、非法运算符、非数字与非有限数输入
- `AppTest`：main 入口的 3 个回归用例（正常输出、参数不足提示、除零友好报错）
- 测试发现的缺陷：`Double.parseDouble` 会静默接受 `"Infinity"` / `"NaN"` 字符串，已通过 `Double.isFinite` 校验修复

## 覆盖率（JaCoCo）

| 类 | 行覆盖 | 分支覆盖 |
|---|---|---|
| Calculator | 100% | 100% |
| App | 90.9% | 100% |

HTML 报告见 `coverage-report/`。

## 静态分析（PMD）

规则集：bestpractices / codestyle / errorprone。剩余警告均为风格级（如 `SystemPrintln` 为 CLI 程序的必要输出），已在实验报告中说明。
