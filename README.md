# 3-colorability

Input format

```
num_variables
varId num_colors ...colors -1st
...
varId num_colors  ...colors -num_variables
num_constraints
varId color varId color -1st
...
varId color varId color -num_constraints
num_colored_variables
varId color varId color -1st
...
varId color varId color -num_colored_variables
```
Example
```
6
0 3 0 1 2
1 3 0 1 2
2 2 0 1
3 3 0 1 2
4 3 0 1 2
5 3 0 1 2
6
0 2 2 0
1 0 2 0
1 1 2 1
3 1 2 1
4 1 2 1
5 0 2 1
1
0 1
```